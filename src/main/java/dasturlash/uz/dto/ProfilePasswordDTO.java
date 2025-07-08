package dasturlash.uz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfilePasswordDTO {
    private String id;
    private String oldPassword;
    private String newPassword;
}
