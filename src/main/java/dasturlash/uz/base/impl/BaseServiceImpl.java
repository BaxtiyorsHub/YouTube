package dasturlash.uz.base.impl;

import dasturlash.uz.base.BaseMapper;
import dasturlash.uz.base.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseServiceImpl<
        R extends JpaRepository<E,String>, // repository
        M extends BaseMapper<D, E>,         // mapper for toEntity, toDTO
        D,                                  // DTO
        E                                   // Entity
        > implements BaseService<D, E> {

    private final R repository;
    private final M mapper;

    public BaseServiceImpl(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}