package dasturlash.uz.service;

import dasturlash.uz.base.BaseService;
import dasturlash.uz.dto.ChannelDTO;
import dasturlash.uz.dto.PlaylistDTO;
import dasturlash.uz.entity.ChannelEntity;
import dasturlash.uz.enums.GeneralStatus;
import org.springframework.data.domain.Page;

public interface ChannelService extends BaseService<ChannelDTO, ChannelEntity> {
    ChannelDTO updatePhoto(String id, ChannelDTO dto);

    ChannelDTO updateBanner(ChannelDTO dto);

    ChannelDTO changeStat(String id, GeneralStatus stat);

    Iterable<ChannelDTO> getUserChannels(String userId);

    Page<ChannelDTO> getAllChannels(int checked, int size);

}
