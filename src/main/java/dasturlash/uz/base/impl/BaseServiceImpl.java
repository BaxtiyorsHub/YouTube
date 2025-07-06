package dasturlash.uz.base.impl;

import dasturlash.uz.base.BaseMapper;
import dasturlash.uz.base.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseServiceImpl<
        R extends JpaRepository<E, String>, // repository
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

    @Override
    public E create(D dto) {
        E entity = mapper.toEntity(dto);
        return repository.save(entity);
    }

    @Override
    public E get(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found!"));
    }
    @Override
    public E update(String id, D dto) {
        E e = get(id);
        return repository.save(mapper.toUpdateEntity(dto, e));
    }
    @Override
    public boolean delete(String id) {
        E e = get(id);
        repository.delete(e);
        return true;
    }

}