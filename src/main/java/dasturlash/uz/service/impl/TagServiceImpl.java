package dasturlash.uz.service.impl;

import dasturlash.uz.base.impl.BaseServiceImpl;
import dasturlash.uz.dto.TagDTO;
import dasturlash.uz.entity.TagEntity;
import dasturlash.uz.mapper.TagMapper;
import dasturlash.uz.repository.TagRepository;
import dasturlash.uz.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl
        extends BaseServiceImpl<TagRepository, TagMapper, TagDTO, TagEntity>
        implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagServiceImpl(TagRepository repository, TagMapper mapper, TagRepository tagRepository, TagMapper tagMapper) {
        super(repository, mapper);
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }


    @Override
    public List<TagDTO> getAll() {
        return tagRepository.findAllEntities()
                .orElseThrow(() -> new RuntimeException("Tag not found"))
                .stream().map(tagMapper::toDTO)
                .toList();
    }
}
