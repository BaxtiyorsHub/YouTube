package dasturlash.uz.service;

import dasturlash.uz.base.BaseService;
import dasturlash.uz.dto.TagDTO;
import dasturlash.uz.entity.TagEntity;

import java.util.List;

public interface TagService extends BaseService<TagDTO, TagEntity> {
    List<TagDTO> getAll();
}
