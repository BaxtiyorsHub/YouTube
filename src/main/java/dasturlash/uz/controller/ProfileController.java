package dasturlash.uz.controller;

import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.service.ProfileService;
import dasturlash.uz.service.impl.ProfileServiceImpl;
import dasturlash.uz.util.SpringSecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/me")
    public ResponseEntity<ProfileEntity> me(){
        return ResponseEntity.ok(profileService.get(SpringSecurityUtil.getUserId()));
    }
}
