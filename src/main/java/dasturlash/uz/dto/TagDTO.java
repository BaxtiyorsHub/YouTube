package dasturlash.uz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TagDTO {
    private String id;
    @NotBlank(message = "Name required")
    private String name;
    private LocalDateTime createdDate;
}
