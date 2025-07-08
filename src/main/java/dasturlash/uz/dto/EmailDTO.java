package dasturlash.uz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailDTO {
    private String id;

    private String email;
    private String body;

    private LocalDateTime createdDate;
}
