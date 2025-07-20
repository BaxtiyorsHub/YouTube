package dasturlash.uz.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tag")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "visible")
    private boolean visible = true;

    @Setter(AccessLevel.NONE)
    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;
}
