package dasturlash.uz.repository;

import dasturlash.uz.dto.ChannelDTO;
import dasturlash.uz.entity.ChannelEntity;
import dasturlash.uz.enums.GeneralStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChannelRepository extends JpaRepository<ChannelEntity, String> {
    @Modifying
    @Transactional
    @Query("update ChannelEntity c set c.status = ?2 where c.id = ?1 and c.visible = true ")
    void changeStatus(String id, GeneralStatus name);

    @Query("from ChannelEntity where profileId = ?1 and visible = true")
    Optional<List<ChannelEntity>> getUserChannels(String userId);

    @Query("from ChannelEntity where visible = true")
    Optional<List<ChannelEntity>> getAll();
}
