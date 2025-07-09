package dasturlash.uz.service.impl;

import dasturlash.uz.base.impl.BaseServiceImpl;
import dasturlash.uz.dto.EmailDTO;
import dasturlash.uz.entity.EmailEntity;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.mapper.EmailMapper;
import dasturlash.uz.repository.EmailRepository;
import dasturlash.uz.service.EmailSenderService;
import dasturlash.uz.util.JwtUtil;
import dasturlash.uz.util.RandomUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmailServiceImpl
        extends BaseServiceImpl<EmailRepository, EmailMapper, EmailDTO, EmailEntity>
        implements EmailSenderService {

    @Value("${spring.mail.username}")
    private String fromAccount;
    @Value("${server.url}")
    private String serverUrl;
    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(EmailRepository repository, EmailMapper mapper, EmailRepository emailRepository, JavaMailSender javaMailSender) {
        super(repository, mapper);
        this.emailRepository = emailRepository;
        this.javaMailSender = javaMailSender;
    }

    /**
     * Via Email Registration method
     * @param email
     * @return String message about sending information
     * */
    public String sendRegistrationCode(String email) {
        Integer smsCode = RandomUtil.fiveDigit();
        String body = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1 style=\"text-align: center\">YouTubega xush kelibsiz.</h1>\n" +
                "<br>\n" +
                "<h4>Ro'yhatdan o'tishni tugatish uchun quyidagi linkga bosing</h4>\n" +
                "<a style=\" background-color: indianred;\n" +
                "  color: black;\n" +
                "  padding: 10px 20px;\n" +
                "  text-align: center;\n" +
                "  text-decoration: none;\n" +
                "  display: inline-block;\"\n" +
                "   href=\"%s/api/v1/auth/registration/email/verification/%s\">Ro'yhatdan\n" +
                "    o'tishni tugatish</a>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        String jwtToken = JwtUtil.encodeForRegistration(email, smsCode);
        body = String.format(body, serverUrl, jwtToken);
        // send
        String registrationComplete = sendMimeMessage("Registration", body, email);
        // save to db
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmail(email);
        emailDTO.setBody(body);
        emailDTO.setCode(smsCode);

        super.create(emailDTO);
        return registrationComplete;
    }

    @SneakyThrows
    @Override
    public boolean isCodeValid(String username, Integer code) {
        Optional<EmailEntity> email = emailRepository.findByEmail(username);
        if (email.isPresent() && email.get().getCode().equals(code)) return true;

        LocalDateTime extDate = email.get().getCreatedDate().plusMinutes(2);
        // now  20:31:30  >  20:32:40    |     now 20:35:30  >  20:32:40
        if (LocalDateTime.now().isAfter(extDate)) return false;

        throw new AppBadException("Code is not valid");
    }

    private String sendMimeMessage(String subject, String body, String toAccount) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            msg.setFrom(fromAccount);

            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(toAccount);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(msg);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "Verification code sent to " + toAccount + "check your email";
    }
}
