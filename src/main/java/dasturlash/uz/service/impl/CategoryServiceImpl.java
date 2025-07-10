package dasturlash.uz.service.impl;

import dasturlash.uz.base.impl.BaseServiceImpl;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.mapper.CategoryMapper;
import dasturlash.uz.repository.CategoryRepository;
import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.service.CategoryService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl
        extends BaseServiceImpl<CategoryRepository, CategoryMapper, CategoryDTO, CategoryEntity>
        implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper, CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        super(repository, mapper);
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Boolean softDelete(String id) {
        try {
            categoryRepository.softDelete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SneakyThrows
    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAllAndVisibleTrue()
                .orElseThrow(() -> new AppBadException("Category not found"))
                .stream().map(categoryMapper::toDTO).toList();
    }
}
