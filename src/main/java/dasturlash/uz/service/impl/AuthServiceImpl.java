package dasturlash.uz.service.impl;

import dasturlash.uz.dto.AuthDTO;
import dasturlash.uz.mapper.AuthMapper;
import dasturlash.uz.base.impl.BaseServiceImpl;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.service.AuthService;
import dasturlash.uz.service.EmailSenderService;
import jakarta.validation.constraints.Email;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl
        extends BaseServiceImpl<ProfileRepository, AuthMapper, AuthDTO, ProfileEntity>
        implements AuthService {

    private final ProfileRepository profileRepository;
    private final EmailSenderService emailSenderService;

    public AuthServiceImpl(ProfileRepository repository, AuthMapper mapper, ProfileRepository profileRepository, EmailSenderService emailSenderService) {
        super(repository, mapper);
        this.profileRepository = profileRepository;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public AuthDTO create(AuthDTO dto) {
        Optional<ProfileEntity> result = profileRepository.findByEmailAndVisibleIsTrue(dto.getEmail());
        if (result.isPresent()) throw new IllegalArgumentException("User already exists");


        return super.create(dto);
    }

    @Override
    public String verification(String token) {


        return "";
    }
}
