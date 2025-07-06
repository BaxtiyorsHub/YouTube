package dasturlash.uz.service.impl;

import dasturlash.uz.mapper.ProfileMapper;
import dasturlash.uz.base.impl.BaseServiceImpl;
import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl
        extends BaseServiceImpl<ProfileRepository, ProfileMapper, ProfileDTO, ProfileEntity>
        implements AuthService {

    public AuthServiceImpl(ProfileRepository repository, ProfileMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public ProfileEntity save(ProfileDTO dto) {
        return null;
    }

    @Override
    public ProfileEntity update(ProfileDTO dto) {
        return null;
    }

    @Override
    public ProfileEntity findById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
