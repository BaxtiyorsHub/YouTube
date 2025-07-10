package dasturlash.uz.repository;

import dasturlash.uz.entity.CategoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    /**
     * @param id -> category id
     * @Note  This method will delete soft
     */
    @Query("update CategoryEntity c set c.visible = false where c.id = ?1")
    @Modifying
    @Transactional
    void softDelete(String id);

    /**
     * This repository method returns categories
     * @return {@code Optional<List<CategoryEntity>>}
     * */
    @Query("select c from CategoryEntity c where c.visible = true")
    Optional<List<CategoryEntity>> findAllAndVisibleTrue();
}
