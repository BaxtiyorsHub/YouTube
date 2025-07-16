package dasturlash.uz.mapper;

import dasturlash.uz.base.BaseMapper;
import dasturlash.uz.dto.PlaylistDTO;
import dasturlash.uz.entity.PlaylistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayListMapper extends BaseMapper<PlaylistDTO, PlaylistEntity> {
    @Override
    PlaylistDTO toDTO(PlaylistEntity entity);

    @Override
    @Mapping(target = "id", ignore = true)
    PlaylistEntity toEntity(PlaylistDTO dto);

    @Override
    @Mapping(target = "id", ignore = true)
    PlaylistEntity toUpdateEntity(PlaylistDTO dto, PlaylistEntity entity);

}
