package dasturlash.uz.service.impl;

import dasturlash.uz.base.impl.BaseServiceImpl;
import dasturlash.uz.dto.ChannelDTO;
import dasturlash.uz.entity.ChannelEntity;
import dasturlash.uz.enums.GeneralStatus;
import dasturlash.uz.mapper.ChannelMapper;
import dasturlash.uz.repository.ChannelRepository;
import dasturlash.uz.service.ChannelService;
import org.springframework.stereotype.Service;

@Service
public class ChannelServiceImpl
        extends BaseServiceImpl<ChannelRepository, ChannelMapper, ChannelDTO, ChannelEntity>
        implements ChannelService {

    public ChannelServiceImpl(ChannelRepository repository, ChannelMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public ChannelDTO updatePhoto(String id, ChannelDTO dto) {
        return null;
    }

    @Override
    public ChannelDTO updateBanner(String id, ChannelDTO dto) {
        return null;
    }

    @Override
    public ChannelDTO changeStat(String id, GeneralStatus stat) {
        return null;
    }

    @Override
    public Iterable<ChannelDTO> getUserChannels(String userId) {
        return null;
    }

    @Override
    public Iterable<ChannelDTO> getAllChannels() {
        return null;
    }
}
