package dasturlash.uz.service;

import dasturlash.uz.base.BaseService;
import dasturlash.uz.dto.AuthDTO;
import dasturlash.uz.entity.ProfileEntity;

public interface AuthService extends BaseService<AuthDTO, ProfileEntity> {

    String verification(String token);
}
