package dasturlash.uz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dasturlash.uz.enums.ChannelStatus;
import dasturlash.uz.enums.GeneralStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaylistInfoDTO extends PlaylistDTO {
    private ChannelStatus status;
    private ChannelDTO channel;
    private ProfileDTO profile;
}
