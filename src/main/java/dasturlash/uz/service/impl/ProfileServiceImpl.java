package dasturlash.uz.service.impl;

import dasturlash.uz.base.impl.BaseServiceImpl;
import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.dto.ProfileDetailDTO;
import dasturlash.uz.dto.ProfileEmailDTO;
import dasturlash.uz.dto.ProfilePasswordDTO;
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

    @Override
    public String changePassword(ProfilePasswordDTO newPassword) {
        Optional<ProfileEntity> optional = profileRepository.findByIdAndVisibleIsTrue(newPassword.getId());
        if(optional.isPresent()){
            ProfileEntity profileEntity = optional.get();
            profileEntity.setPassword(newPassword.getNewPassword());
            profileRepository.save(profileEntity);
            return "password changed";
        }
        return "something went wrong";
    }

    @Override
    public String updateEmail(ProfileEmailDTO profileEmailDTO) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleIsTrue(profileEmailDTO.getOldEmail());
        if(optional.isPresent()){
            ProfileEntity profileEntity = optional.get();
            profileEntity.setEmail(profileEmailDTO.getNewEmail());
            profileRepository.save(profileEntity);
            return "email changed";
        }
        return "something went wrong";
    }

    @Override
    public ProfileDetailDTO updateDetail(ProfileDetailDTO profileDetailDTO) {
        Optional<ProfileEntity> optional = profileRepository.findByIdAndVisibleIsTrue(profileDetailDTO.getId());
        if(optional.isPresent()){
            ProfileEntity profileEntity = optional.get();
            profileEntity.setName(profileDetailDTO.getName());
            profileEntity.setSurname(profileDetailDTO.getSurname());
            profileRepository.save(profileEntity);
            return profileDetailDTO;
        }
        return null;
    }


}
