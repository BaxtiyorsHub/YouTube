package dasturlash.uz.controller;

import dasturlash.uz.dto.TagDTO;
import dasturlash.uz.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping("/create")
    public ResponseEntity<TagDTO> createTag(TagDTO dto){
        return ResponseEntity.ok(tagService.create(dto));
    }

    @PutMapping("/update")
    @Secured("ADMIN")
    public ResponseEntity<TagDTO> updateTag(
            @RequestParam String id,
            @RequestBody TagDTO dto){
        return ResponseEntity.ok(tagService.update(id,dto));
    }

    @DeleteMapping("/delete")
    @Secured("ADMIN")
    public ResponseEntity<Boolean> deleteTag(@RequestParam String id){
        return ResponseEntity.ok(tagService.delete(id));
    }

    @GetMapping("/tag-all")
    @Secured("ADMIN")
    public ResponseEntity<List<TagDTO>> createTag(){
        return ResponseEntity.ok(tagService.getAll());
    }
}
