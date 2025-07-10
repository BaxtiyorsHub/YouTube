package dasturlash.uz.service;

import dasturlash.uz.base.BaseService;
import dasturlash.uz.dto.ChannelDTO;
import dasturlash.uz.entity.ChannelEntity;
import dasturlash.uz.enums.GeneralStatus;

public interface ChannelService extends BaseService<ChannelDTO, ChannelEntity> {
    ChannelDTO updatePhoto(String id, ChannelDTO dto);

    ChannelDTO updateBanner(String id, ChannelDTO dto);

    ChannelDTO changeStat(String id, GeneralStatus stat);

    Iterable<ChannelDTO> getUserChannels(String userId);

    Iterable<ChannelDTO> getAllChannels();

}
