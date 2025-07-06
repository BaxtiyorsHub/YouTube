package dasturlash.uz.mapper;

import dasturlash.uz.base.BaseMapper;
import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<CategoryDTO, CategoryEntity> {

    @Override
    @Mapping(target = "id", ignore = true)
    CategoryEntity toEntity(CategoryDTO dto);

    @Override
    CategoryDTO toDTO(CategoryEntity entity);

    @Override
    @Mapping(target = "id", ignore = true)
    CategoryEntity toUpdateEntity(CategoryDTO dto,@MappingTarget CategoryEntity entity);
}
