package dasturlash.uz.service.impl;

import dasturlash.uz.base.impl.BaseServiceImpl;
import dasturlash.uz.mapper.CategoryMapper;
import dasturlash.uz.repository.CategoryRepository;
import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl
        extends BaseServiceImpl<CategoryRepository, CategoryMapper, CategoryDTO, CategoryEntity>
        implements CategoryService {

    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        super(repository, mapper);
    }
}
