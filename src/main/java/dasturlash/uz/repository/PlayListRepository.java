package dasturlash.uz.repository;

import dasturlash.uz.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayListRepository extends JpaRepository<PlaylistEntity,String> {

}
