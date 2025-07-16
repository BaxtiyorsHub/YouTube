package dasturlash.uz.service;

import dasturlash.uz.base.BaseService;
import dasturlash.uz.dto.ChannelDTO;
import dasturlash.uz.dto.PlaylistDTO;
import dasturlash.uz.entity.PlaylistEntity;

public interface PlayListService extends BaseService<PlaylistDTO, PlaylistEntity> {
    PlaylistDTO changeStat(ChannelDTO dto);
}
