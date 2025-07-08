package dasturlash.uz.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileDTO {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String attachId;
}
