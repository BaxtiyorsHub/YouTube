package dasturlash.uz.service.impl;

import dasturlash.uz.entity.AttachEntity;
import dasturlash.uz.service.AttachService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

public class AttachServiceImpl implements
        AttachService {
    @Override
    public List<AttachEntity> getPagia() {
        Pageable pageable = new PageRequest(0, 10);
        Page<AttachEntity> page = new PageImpl<AttachEntity>(new ArrayList<AttachEntity>());
    }
}
