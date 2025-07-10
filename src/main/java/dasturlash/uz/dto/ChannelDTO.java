package dasturlash.uz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelDTO {
    private String id;
    private String name;
    private String photoId;
    private AttachDTO photo;
    private String description;
    private String banner;
    private String profileId;
    private ProfileDTO profile;
}
