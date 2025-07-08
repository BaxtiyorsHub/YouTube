package dasturlash.uz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "email_history")
@Entity
@Setter
@Getter
public class EmailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "to_account")
    private String email;

    @Column(name = "body", columnDefinition = "text")
    private String body;

    @Column(name = "code")
    private Integer code;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

}
