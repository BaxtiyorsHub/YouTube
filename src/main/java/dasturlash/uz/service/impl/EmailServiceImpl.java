package dasturlash.uz.service.impl;

import dasturlash.uz.base.impl.BaseServiceImpl;
import dasturlash.uz.dto.EmailDTO;
import dasturlash.uz.entity.EmailEntity;
import dasturlash.uz.mapper.EmailMapper;
import dasturlash.uz.repository.EmailRepository;
import dasturlash.uz.service.EmailSenderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl
        extends BaseServiceImpl<EmailRepository, EmailMapper, EmailDTO, EmailEntity>
        implements EmailSenderService {

    private final EmailRepository emailRepository;

    public EmailServiceImpl(EmailRepository repository, EmailMapper mapper, EmailRepository emailRepository) {
        super(repository, mapper);
        this.emailRepository = emailRepository;
    }
    @Override
    public EmailDTO create(EmailDTO dto) {
        List<EmailEntity> byEmail = emailRepository.findByEmail(dto.getEmail());
        return super.create(dto);
    }
    
}
