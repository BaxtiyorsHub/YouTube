package dasturlash.uz.service.impl;

import dasturlash.uz.base.impl.BaseServiceImpl;
import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.mapper.ProfileMapper;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.service.ProfileService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl
        extends BaseServiceImpl<ProfileRepository, ProfileMapper, ProfileDTO, ProfileEntity>
        implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository repository, ProfileMapper mapper, ProfileRepository profileRepository) {
        super(repository, mapper);
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<ProfileEntity> findByUsernameAndVisibleIsTrue(String username) {
        return profileRepository.findByEmailAndVisibleIsTrue(username).or(Optional::empty);
    }
}
