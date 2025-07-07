package dasturlash.uz.dto;

import dasturlash.uz.enums.ProfileRole;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class JwtDTO {
    private String username;
    private ProfileRole role;
    private Integer code;

    public JwtDTO(String username, ProfileRole role) {
        this.username = username;
        this.role = role;
    }

    public JwtDTO(String username,Integer code) {
        this.username = username;
        this.code = code;
    }
}
