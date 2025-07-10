package dasturlash.uz.service.impl;

import dasturlash.uz.base.impl.BaseServiceImpl;
import dasturlash.uz.dto.TagDTO;
import dasturlash.uz.entity.TagEntity;
import dasturlash.uz.mapper.TagMapper;
import dasturlash.uz.repository.TagRepository;
import dasturlash.uz.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl
        extends BaseServiceImpl<TagRepository, TagMapper, TagDTO, TagEntity>
        implements TagService {

    public TagServiceImpl(TagRepository repository, TagMapper mapper) {
        super(repository, mapper);
    }

}
