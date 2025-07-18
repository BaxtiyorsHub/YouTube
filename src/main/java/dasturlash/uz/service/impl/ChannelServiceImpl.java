package dasturlash.uz.service.impl;

import dasturlash.uz.base.impl.BaseServiceImpl;
import dasturlash.uz.dto.ChannelDTO;
import dasturlash.uz.dto.PlaylistDTO;
import dasturlash.uz.entity.ChannelEntity;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.enums.GeneralStatus;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.mapper.ChannelMapper;
import dasturlash.uz.repository.ChannelRepository;
import dasturlash.uz.service.ChannelService;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @SneakyThrows
    @Override
    public ChannelDTO updatePhoto(String id, ChannelDTO dto) {
        generalCheck(id, dto.getProfileId());

        return super.update(id, dto);
    }

    @Override
    @SneakyThrows
    public ChannelDTO updateBanner(ChannelDTO dto) {
        ChannelEntity channel = generalCheck(dto.getId(), dto.getProfileId());

        channel.setBanner(dto.getBanner());
        return super.update(dto.getId(), dto);
    }

    @SneakyThrows
    @Override
    public ChannelDTO changeStat(String id, GeneralStatus stat) {
        if (!id.isBlank() && stat != null) {
            channelRepository.changeStatus(id, stat);
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
    public Page<ChannelDTO> getAllChannels(int checked, int size) {

        PageRequest pageRequest = PageRequest.of(checked, size, Sort.by("createdDate").descending());

        Page<ChannelEntity> dtoPage = channelRepository.findAll(pageRequest);

        List<ChannelDTO> response = dtoPage.getContent()
                .stream()
                .map(channelMapper::toDTO)
                .toList();

        return new PageImpl<>(response, pageRequest, dtoPage.getTotalElements());
    }

    private ChannelEntity generalCheck(String id, String profileId) throws AppBadException, Exception {
        ChannelEntity channel = channelRepository.findById(id)
                .orElseThrow(Exception::new);

        ProfileEntity profile = channel.getProfile();

        if (!profile.getId().equals(profileId)) throw new AppBadException("Something went wrong channel");
        if (!channel.getStatus().equals(GeneralStatus.ACTIVE)) throw new AppBadException("Channel not active");
        if (profile.getRole().equals(ProfileRole.USER) || profile.getRole().equals(ProfileRole.SUPER_ADMIN))
            throw new AppBadException("You can't change banner");
        return channel;
    }
}
