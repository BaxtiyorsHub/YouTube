package dasturlash.uz.repository;

import dasturlash.uz.entity.ChannelEntity;
import dasturlash.uz.enums.GeneralStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<ChannelEntity, String> {
    @Modifying
    @Transactional
    @Query("update ChannelEntity c set c.status = ? where c.id = ?1 and c.visible = true ")
    void changeStatus(String id, GeneralStatus name);
}
