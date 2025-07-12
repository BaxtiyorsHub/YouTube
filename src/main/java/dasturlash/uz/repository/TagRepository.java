package dasturlash.uz.repository;

import dasturlash.uz.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, String> {
    @Query("select t from TagEntity t where t.visible = true")
    Optional<List<TagEntity>> findAllEntities();
}
