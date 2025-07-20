package dasturlash.uz.controller;

import dasturlash.uz.dto.ChannelDTO;
import dasturlash.uz.dto.PlaylistDTO;
import dasturlash.uz.dto.PlaylistInfoDTO;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.service.PlayListService;
import dasturlash.uz.util.PageCheckUtil;
import dasturlash.uz.util.SpringSecurityUtil;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlayListService playListService;

    @PostMapping("/create")
    @PermitAll
    public ResponseEntity<PlaylistDTO> createPlaylist(
            @RequestBody PlaylistDTO dto
    ) {
        ProfileEntity currentProfile = SpringSecurityUtil.getCurrentProfile();
        if (currentProfile == null || !currentProfile.getId().equals(dto.getProfileId())) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(playListService.create(dto));
    }

    @PutMapping("/update")
    @RolesAllowed({"USER","OWNER"})
    public ResponseEntity<PlaylistDTO> update(
            @RequestBody PlaylistDTO dto
    ) {
        return ResponseEntity.ok(playListService.update(dto.getId(), dto));
    }

    @DeleteMapping("/delete")
    @RolesAllowed({"USER","OWNER","ADMIN"})
    public ResponseEntity<Boolean> deletePlaylist(@RequestParam String id){
        return ResponseEntity.ok(playListService.delete(id));
    }

    @PutMapping("/change-stat")
    @RolesAllowed({"USER","OWNER"})
    public ResponseEntity<PlaylistDTO> changeStat(
            @RequestBody ChannelDTO dto) {
        return ResponseEntity.ok(playListService.changeStat(dto));
    }

   @GetMapping("/getPlaylist")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Page<PlaylistInfoDTO>> getPlaylist(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
   ){
       int page1 = PageCheckUtil.page(page);
       return ResponseEntity.ok(playListService.getPlaylists(page1,size));
   }


}
