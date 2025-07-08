package dasturlash.uz.base;

public interface BaseService<D, E> {
    D create(D dto);
    E getEntity(String id);
    D getDTO(String id);
    D update(String id,D dto);
    boolean delete(String id);
}
