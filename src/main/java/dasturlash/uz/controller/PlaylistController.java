package dasturlash.uz.controller;

import dasturlash.uz.config.SpringSecurityConfig;
import dasturlash.uz.dto.ChannelDTO;
import dasturlash.uz.dto.PlaylistDTO;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.service.PlayListService;
import dasturlash.uz.util.SpringSecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlayListService playListService;

    @PostMapping("/create")
    public ResponseEntity<PlaylistDTO> createPlaylist(
            @RequestBody PlaylistDTO dto
    ) {
        ProfileEntity currentProfile = SpringSecurityUtil.getCurrentProfile();
        if (currentProfile == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(playListService.create(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<PlaylistDTO> update(
            @RequestBody PlaylistDTO dto
    ) {
        return ResponseEntity.ok(playListService.update(dto.getId(), dto));
    }

    @PutMapping("/change-stat")
    public ResponseEntity<PlaylistDTO> changeStat(
            @RequestBody ChannelDTO dto) {
        return ResponseEntity.ok(playListService.changeStat(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deletePlaylist(@RequestParam String id){
        return ResponseEntity.ok(playListService.delete(id));
    }
}
