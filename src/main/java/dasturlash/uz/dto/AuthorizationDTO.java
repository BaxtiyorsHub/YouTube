package dasturlash.uz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorizationDTO {
    @NotBlank(message = "Username required")
    private String email;
    @NotBlank(message = "password required")
    private String password;
}
