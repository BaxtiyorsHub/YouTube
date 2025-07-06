package dasturlash.uz.controller;

import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.service.CategoryService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    @PermitAll
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryDTO dto){
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryEntity> updateCategory(
            @RequestBody CategoryDTO dto,
            @PathVariable String id){
        return ResponseEntity.ok(categoryService.update(id,dto));
    }

}
