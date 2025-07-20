package dasturlash.uz.service.impl;

import dasturlash.uz.base.impl.BaseServiceImpl;
import dasturlash.uz.dto.ChannelDTO;
import dasturlash.uz.dto.PlaylistDTO;
import dasturlash.uz.dto.PlaylistInfoDTO;
import dasturlash.uz.entity.ChannelEntity;
import dasturlash.uz.entity.PlaylistEntity;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.mapper.ChannelMapper;
import dasturlash.uz.mapper.PlayListMapper;
import dasturlash.uz.mapper.ProfileMapper;
import dasturlash.uz.repository.PlayListRepository;
import dasturlash.uz.service.ChannelService;
import dasturlash.uz.service.PlayListService;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayListServiceImpl
        extends BaseServiceImpl<PlayListRepository, PlayListMapper, PlaylistDTO, PlaylistEntity>
        implements PlayListService {

    private final PlayListRepository playListRepository;
    private final PlayListMapper playListMapper;
    private final ChannelService channelService;
    private final ChannelMapper channelMapper;
    private final ProfileMapper profileMapper;

    public PlayListServiceImpl(PlayListRepository repository, PlayListMapper mapper, PlayListRepository playListRepository, PlayListMapper playListMapper, ChannelService channelService, ChannelMapper channelMapper, ProfileMapper profileMapper) {
        super(repository, mapper);
        this.playListRepository = playListRepository;
        this.playListMapper = playListMapper;
        this.channelService = channelService;
        this.channelMapper = channelMapper;
        this.profileMapper = profileMapper;
    }

    @SneakyThrows
    @Override
    public PlaylistDTO changeStat(ChannelDTO dto) {
        if (dto.getId() == null || dto.getStatus() == null) throw new Exception("Something went wrong");
        PlaylistEntity db = playListRepository
                .findById(dto.getId())
                .orElseThrow(Exception::new);

        db.setStatus(dto.getChannelStatus());

        return playListMapper.toDTO(playListRepository.save(db));
    }

    @Override
    @SneakyThrows
    public Page<PlaylistInfoDTO> getPlaylists(int page1, int size) {
        PageRequest pageable = PageRequest.of(page1, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        Page<PlaylistEntity> all = playListRepository.findAll(pageable);
        if (!all.hasContent()) throw new AppBadException("Playlist not found");

        List<PlaylistInfoDTO> response = all.getContent()
                .stream()
                .map(this::toInfo)
                .toList();

        return new PageImpl<>(response,pageable,all.getTotalElements());
    }

    private PlaylistInfoDTO toInfo(PlaylistEntity dto) {
        PlaylistInfoDTO info = new PlaylistInfoDTO();

        info.setId(dto.getId());
        info.setName(dto.getName());
        info.setStatus(dto.getStatus());
        info.setDescription(dto.getDescription());
        info.setOrderNum(dto.getOrderNum());
        info.setChannel(channelMapper.toDTO(dto.getChannel()));
        info.setProfile(profileMapper.toDTO(dto.getProfile()));
        return info;
    }


}
