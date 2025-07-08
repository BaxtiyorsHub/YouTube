package dasturlash.uz.service;

import dasturlash.uz.base.BaseService;
import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.dto.ProfilePasswordDTO;
import dasturlash.uz.entity.ProfileEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileService extends BaseService<ProfileDTO, ProfileEntity> {

    /**
     * GetProfile by username
     * @param username
     * @return {@code Optional<ProfileEntity>}
     * */
    @Query("select p from ProfileEntity p where p.email=?1 and p.visible = true ")
    Optional<ProfileEntity> findByUsernameAndVisibleIsTrue(String username);

    Boolean changePassword(ProfilePasswordDTO newPassword);
}
