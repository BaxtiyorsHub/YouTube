package dasturlash.uz.service.impl;

import dasturlash.uz.base.impl.BaseServiceImpl;
import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.mapper.ProfileMapper;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.service.ProfileService;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl
        extends BaseServiceImpl<ProfileRepository, ProfileMapper, ProfileDTO, ProfileEntity>
        implements ProfileService {

    public ProfileServiceImpl(ProfileRepository repository, ProfileMapper mapper) {
        super(repository, mapper);
    }
}
