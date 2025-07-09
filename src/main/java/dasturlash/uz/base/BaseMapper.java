package dasturlash.uz.base;

public interface BaseMapper<D, E> {
    E toEntity(D dto);

    D toDTO(E entity);

    E toUpdateEntity(D dto, E entity);

}
