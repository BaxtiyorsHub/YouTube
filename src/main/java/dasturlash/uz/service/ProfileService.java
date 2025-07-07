package dasturlash.uz.service;

import dasturlash.uz.base.BaseService;
import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.entity.ProfileEntity;

import java.util.Optional;

public interface ProfileService extends BaseService<ProfileDTO, ProfileEntity> {

    Optional<ProfileEntity> findByUsernameAndVisibleIsTrue(String username);
}
