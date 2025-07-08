package dasturlash.uz.mapper;

import dasturlash.uz.base.BaseMapper;
import dasturlash.uz.dto.EmailDTO;
import dasturlash.uz.entity.EmailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmailMapper extends BaseMapper<EmailDTO, EmailEntity> {
    @Override
    @Mapping(target = "id", ignore = true)
    EmailEntity toEntity(EmailDTO dto);

    @Override
    EmailDTO toDTO(EmailEntity entity);

    @Override
    @Mapping(target = "id", ignore = true)
    EmailEntity toUpdateEntity(EmailDTO dto, @MappingTarget EmailEntity entity);
}
