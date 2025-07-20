package dasturlash.uz.service;

import dasturlash.uz.base.BaseService;
import dasturlash.uz.dto.ChannelDTO;
import dasturlash.uz.dto.PlaylistDTO;
import dasturlash.uz.dto.PlaylistInfoDTO;
import dasturlash.uz.entity.PlaylistEntity;
import org.springframework.data.domain.Page;

public interface PlayListService extends BaseService<PlaylistDTO, PlaylistEntity> {
    PlaylistDTO changeStat(ChannelDTO dto);

    Page<PlaylistInfoDTO> getPlaylists(int page1, int size);
}
