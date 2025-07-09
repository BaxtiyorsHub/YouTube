package dasturlash.uz.controller;

import dasturlash.uz.dto.AuthDTO;
import dasturlash.uz.dto.AuthorizationDTO;
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
    public ResponseEntity<String> register(
            @RequestBody AuthDTO dto
    ){
        return ResponseEntity.ok(authService.createRegistrationCode(dto));
    }

    @PutMapping("/verification/{token}")
    public ResponseEntity<String> verification(@PathVariable String token){
        return ResponseEntity.ok(authService.verification(token));
    }

    @GetMapping("/login")
    public ResponseEntity<AuthDTO> login(@RequestBody AuthorizationDTO authorization){
        return ResponseEntity.ok(authService.login(authorization));
    }
}
