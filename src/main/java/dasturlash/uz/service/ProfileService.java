package dasturlash.uz.service;

import dasturlash.uz.base.BaseService;
import dasturlash.uz.dto.ProfileDetailDTO;
import dasturlash.uz.dto.ProfileEmailDTO;
import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.dto.ProfilePasswordDTO;
import dasturlash.uz.entity.ProfileEntity;

import java.util.Optional;

public interface ProfileService extends BaseService<ProfileDTO, ProfileEntity> {

    /**
     * GetProfile by username
     * @param username
     * @return {@code Optional<ProfileEntity>}
     * */
    Optional<ProfileEntity> findByUsernameAndVisibleIsTrue(String username);

    String changePassword(ProfilePasswordDTO newPassword);

    String updateEmail(ProfileEmailDTO profileEmailDTO);

    ProfileDetailDTO updateDetail(ProfileDetailDTO profileDetailDTO);
}
