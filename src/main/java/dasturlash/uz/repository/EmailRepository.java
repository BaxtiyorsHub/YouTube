package dasturlash.uz.repository;

import dasturlash.uz.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<EmailEntity, String> {

    List<EmailEntity> findByEmail(String email);
}
