package dasturlash.uz.base.impl;

import dasturlash.uz.base.BaseMapper;
import dasturlash.uz.base.BaseService;
import jakarta.transaction.Transactional;
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
    @Transactional
    public D create(D dto) {
        if (dto == null) throw new RuntimeException("DTO can't be null!");
        return mapper
                .toDTO(repository
                        .save(mapper
                                .toEntity(dto)));
    }

    @Override
    public E getEntity(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found!"));
    }

    @Override
    public D getDTO(String id) {
        return repository.findById(id).map(mapper::toDTO).orElseThrow(() -> new RuntimeException("Not found!"));
    }

    @Override
    @Transactional
    public D update(String id, D dto) {
        E e = getEntity(id);
        return mapper
                .toDTO(
                        repository.save(mapper.toUpdateEntity(dto, e)));
    }

    @Override
    @Transactional
    public boolean delete(String id) {
        repository.delete(getEntity(id));
        return true;
    }

    @Override
    public D save(E entity) {
        if (entity == null) throw new RuntimeException("Entity can't be null!");
        return mapper.toDTO(repository.save(entity));
    }
}