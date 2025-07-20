package dasturlash.uz.mapper;

import dasturlash.uz.base.BaseMapper;
import dasturlash.uz.dto.ChannelDTO;
import dasturlash.uz.entity.ChannelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ChannelMapper extends BaseMapper<ChannelDTO, ChannelEntity> {
    @Override
    ChannelDTO toDTO(ChannelEntity entity);

    @Override
    @Mapping(target = "id", ignore = true)
    ChannelEntity toEntity(ChannelDTO dto);

    @Override
    @Mapping(target = "id", ignore = true)
    ChannelEntity toUpdateEntity(ChannelDTO dto, @MappingTarget ChannelEntity entity);

}
