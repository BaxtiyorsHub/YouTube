package dasturlash.uz.mapper;

import dasturlash.uz.base.BaseMapper;
import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.entity.ProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfileMapper extends BaseMapper<ProfileDTO, ProfileEntity> {
    @Override
    @Mapping(target = "id", ignore = true)
    ProfileEntity toEntity(ProfileDTO dto);

    @Override
    ProfileDTO toDTO(ProfileEntity entity);

    @Override
    @Mapping(target = "id", ignore = true)
    ProfileEntity toUpdateEntity(ProfileDTO dto, @MappingTarget ProfileEntity entity);
}
