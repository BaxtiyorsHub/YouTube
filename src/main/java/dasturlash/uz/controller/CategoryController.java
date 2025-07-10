package dasturlash.uz.controller;

import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.service.CategoryService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    @Secured("ADMIN")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO dto){
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @PutMapping("/update/{id}")
    @Secured("ADMIN")
    public ResponseEntity<CategoryDTO> updateCategory(
            @RequestBody CategoryDTO dto,
            @PathVariable String id){
        return ResponseEntity.ok(categoryService.update(id,dto));
    }

    @DeleteMapping("/delete/{id}")
    @Secured("ADMIN")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable String id){
        return ResponseEntity.ok(categoryService.softDelete(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<CategoryDTO>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
