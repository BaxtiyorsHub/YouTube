package dasturlash.uz.controller;

import dasturlash.uz.dto.AuthDTO;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthDTO> register(
            @RequestBody AuthDTO dto
    ){
        return ResponseEntity.ok(authService.create(dto));
    }

    @PutMapping("/verification")
    public ResponseEntity<String> verification(@PathVariable String token){
        return ResponseEntity.ok(authService.verification(token));
    }
}
