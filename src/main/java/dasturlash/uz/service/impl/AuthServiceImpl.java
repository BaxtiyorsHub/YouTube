package dasturlash.uz.service.impl;

import dasturlash.uz.dto.AuthDTO;
import dasturlash.uz.dto.AuthorizationDTO;
import dasturlash.uz.dto.JwtDTO;
import dasturlash.uz.enums.GeneralStatus;
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
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl
        extends BaseServiceImpl<ProfileRepository, AuthMapper, AuthDTO, ProfileEntity>
        implements AuthService {

    private final EmailSenderService emailSenderService;
    private final ProfileService profileService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthMapper authMapper;


    public AuthServiceImpl(ProfileRepository repository, AuthMapper mapper,
                           EmailSenderService emailSenderService, ProfileService profileService,
                           BCryptPasswordEncoder bCryptPasswordEncoder, AuthMapper authMapper) {
        super(repository, mapper);
        this.emailSenderService = emailSenderService;
        this.profileService = profileService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authMapper = authMapper;
    }

    @SneakyThrows
    @Override
    public String createRegistrationCode(AuthDTO dto) {
        Optional<ProfileEntity> result = profileService.findByUsernameAndVisibleIsTrue(dto.getEmail());
        if (result.isPresent()) {
            if (result.get().getStatus().equals(GeneralStatus.NOT_ACTIVE)) {
                profileService.delete(result.get().getId());
            }
            throw new AppBadException("Username already exists");
        }
        ProfileEntity entity = authMapper.toEntity(dto);
        entity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        profileService.save(entity);

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

        if (!profileEntity.getStatus().equals(GeneralStatus.NOT_ACTIVE))
            throw new AppBadException("User already verified");

        if (emailSenderService.isCodeValid(decoded.getUsername(), decoded.getCode())) return "Verification successful";
        throw new AppBadException("Verification is not complete");
    }

    @SneakyThrows
    @Override
    public AuthDTO login(AuthorizationDTO authorization) {
        Optional<ProfileEntity> profile = profileService.findByUsernameAndVisibleIsTrue(authorization.getEmail());
        if (profile.isPresent()) {
            ProfileEntity profileEntity = profile.get();
            if (!profileEntity.getStatus().equals(GeneralStatus.ACTIVE))
                throw new AppBadException("Profile field not match");
            if (!bCryptPasswordEncoder.matches(authorization.getPassword(), profileEntity.getPassword())) {
                throw new AppBadException("Username or password wrong");
            }
            AuthDTO dto = authMapper.toDTO(profileEntity);
            dto.setJwt(JwtUtil.encode(profileEntity.getEmail(), profileEntity.getRole()));
            dto.setRefreshToken(JwtUtil.refreshToken(profileEntity.getEmail(), profileEntity.getRole().name()));
            return dto;
        }
        throw new AppBadException("User not found");
    }
}
