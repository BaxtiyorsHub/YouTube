package dasturlash.uz.service.impl;

import dasturlash.uz.dto.AuthDTO;
import dasturlash.uz.dto.JwtDTO;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.mapper.AuthMapper;
import dasturlash.uz.base.impl.BaseServiceImpl;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.service.AuthService;
import dasturlash.uz.service.EmailSenderService;
import dasturlash.uz.service.ProfileService;
import dasturlash.uz.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.validation.constraints.Email;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl
        extends BaseServiceImpl<ProfileRepository, AuthMapper, AuthDTO, ProfileEntity>
        implements AuthService {

    private final EmailSenderService emailSenderService;
    private final ProfileService profileService;

    public AuthServiceImpl(ProfileRepository repository, AuthMapper mapper, EmailSenderService emailSenderService, ProfileService profileService) {
        super(repository, mapper);
        this.emailSenderService = emailSenderService;
        this.profileService = profileService;
    }

    @SneakyThrows
    @Override
    public String createRegistrationCode(AuthDTO dto) {
        Optional<ProfileEntity> result = profileService.findByUsernameAndVisibleIsTrue(dto.getEmail());
        if (result.isPresent()) {
            if (result.get().getStatus().equals(ProfileStatus.NOT_ACTIVE)){
                profileService.delete(result.get().getId());
            }
            throw new AppBadException("Username already exists");
        }

        return emailSenderService.sendRegistrationCode(result.get().getEmail());
    }

    @SneakyThrows
    @Override
    public String verification(String token) {
        JwtDTO decoded;
        try {
            decoded = JwtUtil.decodeRegistrationToken(token);
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("Token expired");
        } catch (JwtException e) {
            throw new AppBadException("Jwt not valid");
        }

        ProfileEntity profileEntity = profileService
                .findByUsernameAndVisibleIsTrue(decoded.getUsername())
                .orElseThrow(() -> new AppBadException("User not found"));

        if (!profileEntity.getStatus().equals(ProfileStatus.NOT_ACTIVE)) throw new AppBadException("User already verified");





        return "";
    }
}
