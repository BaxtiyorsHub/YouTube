package dasturlash.uz.service;

import dasturlash.uz.base.BaseService;
import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.entity.CategoryEntity;

import java.util.List;

public interface CategoryService extends BaseService<CategoryDTO, CategoryEntity> {
    Boolean softDelete(String id);
    List<CategoryDTO> getAllCategories();
}
