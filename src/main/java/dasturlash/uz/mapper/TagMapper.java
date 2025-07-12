package dasturlash.uz.mapper;

import dasturlash.uz.base.BaseMapper;
import dasturlash.uz.dto.TagDTO;
import dasturlash.uz.entity.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagMapper extends BaseMapper<TagDTO, TagEntity> {
    @Override
    TagDTO toDTO(TagEntity entity);

    @Override
    @Mapping(target = "id", ignore = true)
    TagEntity toEntity(TagDTO dto);

    @Override
    @Mapping(target = "id", ignore = true)
    TagEntity toUpdateEntity(TagDTO dto, TagEntity entity);
}
