package dasturlash.uz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorizationDTO {
    @NotBlank(message = "Username required")
    private String email;
    @NotBlank(message = "password required")
    private String password;
}
