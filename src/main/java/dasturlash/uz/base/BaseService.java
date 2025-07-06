package dasturlash.uz.base;

public interface BaseService<D, E> {
    E create(D dto);
    E get(String id);
    E update(String id,D dto);
    boolean delete(String id);
}
