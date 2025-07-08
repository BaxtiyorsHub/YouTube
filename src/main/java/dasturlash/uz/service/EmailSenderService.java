package dasturlash.uz.service;

import dasturlash.uz.base.BaseService;
import dasturlash.uz.dto.EmailDTO;
import dasturlash.uz.entity.EmailEntity;
import jakarta.validation.constraints.NotBlank;

public interface EmailSenderService extends BaseService<EmailDTO, EmailEntity> {

    String sendRegistrationCode(@NotBlank(message = "Username required") String email);

    boolean isCodeValid(String username, Integer code);
}
