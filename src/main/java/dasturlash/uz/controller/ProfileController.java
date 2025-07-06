package dasturlash.uz.controller;

import dasturlash.uz.service.ProfileService;
import dasturlash.uz.service.impl.ProfileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
}
