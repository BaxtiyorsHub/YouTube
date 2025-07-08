package dasturlash.uz.service;

import dasturlash.uz.base.BaseService;
import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.entity.ProfileEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileService extends BaseService<ProfileDTO, ProfileEntity> {

    /**
     * GetProfile by username
     * @param username
     * @return {@code Optional<ProfileEntity>}
     * */
    Optional<ProfileEntity> findByUsernameAndVisibleIsTrue(String username);
}
