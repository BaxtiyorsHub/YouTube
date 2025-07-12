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

import java.util.List;

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
    @SneakyThrows
    public ChannelDTO updateBanner(ChannelDTO dto) {
        ChannelEntity channel = channelRepository.findById(dto.getId())
                .orElseThrow(Exception::new);
        if (!channel.getProfileId().equals(dto.getProfileId())) throw new AppBadException("Something went wrong channel");
        // ownerlarga chcek qilish kerak
        channel.setBanner(dto.getBanner());
        return null;
    }

    @SneakyThrows
    @Override
    public ChannelDTO changeStat(String id, GeneralStatus stat) {
        if (!id.isBlank() && stat != null) {
            channelRepository.changeStatus(id,stat);
            return channelMapper.toDTO(channelRepository.findById(id).orElseThrow());
        }
        throw new AppBadException("WTH is that piece of shit! channel");
    }

    @Override
    @SneakyThrows
    public Iterable<ChannelDTO> getUserChannels(String userId) {
        return channelRepository.getUserChannels(userId)
                .orElseThrow(Exception::new)
                .stream()
                .map(channelMapper::toDTO)
                .toList();
    }

    @Override
    @SneakyThrows
    public List<ChannelDTO> getAllChannels() {
        return channelRepository.getAll()
                .orElseThrow(Exception::new)
                .stream()
                .map(channelMapper::toDTO)
                .toList();
    }
}
