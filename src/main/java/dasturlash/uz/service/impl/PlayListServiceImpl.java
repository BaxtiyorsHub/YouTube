package dasturlash.uz.service.impl;

import dasturlash.uz.base.impl.BaseServiceImpl;
import dasturlash.uz.dto.ChannelDTO;
import dasturlash.uz.dto.PlaylistDTO;
import dasturlash.uz.entity.PlaylistEntity;
import dasturlash.uz.mapper.PlayListMapper;
import dasturlash.uz.repository.PlayListRepository;
import dasturlash.uz.service.PlayListService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayListServiceImpl
        extends BaseServiceImpl<PlayListRepository, PlayListMapper, PlaylistDTO, PlaylistEntity>
        implements PlayListService {

    private final PlayListRepository playListRepository;

    public PlayListServiceImpl(PlayListRepository repository, PlayListMapper mapper, PlayListRepository playListRepository) {
        super(repository, mapper);
        this.playListRepository = playListRepository;
    }

    @SneakyThrows
    @Override
    public PlaylistDTO changeStat(ChannelDTO dto) {
        PlaylistEntity db = playListRepository
                .findById(dto.getId())
                .orElseThrow(Exception::new);

        db.setStatus(dto.getStatus());

        return null;
    }
}
