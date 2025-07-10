package dasturlash.uz.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "attach")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttachEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "file_size")
    private long size;

    @Column(name = "extension")
    private String extension;

    @Column(name = "path")
    private String path;

    @Column(name = "duration")
    private String duration;

    @Column(name = "visible")
    private boolean visible = true;

    @CreationTimestamp
    @Column(name = "created_date")
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdDate;
}
