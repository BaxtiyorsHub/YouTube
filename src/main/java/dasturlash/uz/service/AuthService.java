package dasturlash.uz.service;

import dasturlash.uz.base.BaseService;
import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.entity.ProfileEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService extends BaseService<ProfileDTO, ProfileEntity> {

}
