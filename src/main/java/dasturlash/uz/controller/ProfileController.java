package dasturlash.uz.controller;

import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.dto.ProfileDetailDTO;
import dasturlash.uz.dto.ProfileEmailDTO;
import dasturlash.uz.dto.ProfilePasswordDTO;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.service.ProfileService;
import dasturlash.uz.service.impl.ProfileServiceImpl;
import dasturlash.uz.util.SpringSecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/me")
    public ResponseEntity<ProfileDTO> me(){
        return ResponseEntity.ok(profileService.getDTO(SpringSecurityUtil.getUserId()));
    }

    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ProfilePasswordDTO passwordDTO){
        return ResponseEntity.ok(profileService.changePassword(passwordDTO));
    }

    @PutMapping("/changeEmail")
    public ResponseEntity<String> changeEmail(@RequestBody ProfileEmailDTO profileEmailDTO){
        return ResponseEntity.ok(profileService.updateEmail(profileEmailDTO));
    }

    @PutMapping("/updateDetail")
    public ResponseEntity<ProfileDetailDTO> updateDetail(@RequestBody ProfileDetailDTO profileDetailDTO){
        return ResponseEntity.ok(profileService.updateDetail(profileDetailDTO));
    }

}
