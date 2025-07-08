package dasturlash.uz.mapper;

import dasturlash.uz.base.BaseMapper;
import dasturlash.uz.dto.AuthDTO;
import dasturlash.uz.entity.ProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthMapper extends BaseMapper<AuthDTO, ProfileEntity> {
    @Override
    ProfileEntity toEntity(AuthDTO dto);

    @Override
    AuthDTO toDTO(ProfileEntity entity);

    @Override
    @Mapping(target = "id", ignore = true)
    ProfileEntity toUpdateEntity(AuthDTO dto, @MappingTarget ProfileEntity entity);
}
