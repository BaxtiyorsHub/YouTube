package dasturlash.uz.repository;

import dasturlash.uz.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<EmailEntity, String> {

    @Query("select e from EmailEntity e where e.email = ?1 order by e.createdDate desc limit 1")
    Optional<EmailEntity> findByEmail(String email);
}
