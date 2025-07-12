package dasturlash.uz.service.impl;

import dasturlash.uz.base.impl.BaseServiceImpl;
import dasturlash.uz.dto.ChannelDTO;
import dasturlash.uz.entity.ChannelEntity;
import dasturlash.uz.enums.GeneralStatus;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.mapper.ChannelMapper;
import dasturlash.uz.repository.ChannelRepository;
import dasturlash.uz.service.ChannelService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class ChannelServiceImpl
        extends BaseServiceImpl<ChannelRepository, ChannelMapper, ChannelDTO, ChannelEntity>
        implements ChannelService {

    private final ChannelRepository channelRepository;
    private final ChannelMapper channelMapper;

    public ChannelServiceImpl(ChannelRepository repository, ChannelMapper mapper, ChannelRepository channelRepository, ChannelMapper channelMapper) {
        super(repository, mapper);
        this.channelRepository = channelRepository;
        this.channelMapper = channelMapper;
    }

    @Override
    public ChannelDTO updatePhoto(String id, ChannelDTO dto) {
        return null;
    }

    @Override
    public ChannelDTO updateBanner(String id, ChannelDTO dto) {
        return null;
    }

    @SneakyThrows
    @Override
    public ChannelDTO changeStat(String id, GeneralStatus stat) {
        if (!id.isBlank() && stat != null) {
            channelRepository.changeStatus(id,stat);
            return channelMapper.toDTO(channelRepository.findById(id).orElseThrow());
        }
        throw new AppBadException("WTH is that piece of shit!");
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
