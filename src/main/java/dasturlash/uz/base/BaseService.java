package dasturlash.uz.base;

public interface BaseService<D, E> {
    E save(D dto);
    E update(D dto);
    E findById(String id);
    void delete(String id);
}
