package dasturlash.uz.entity;

import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Setter
@Getter
@Table(name = "profile")
@Entity
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

   @Column(name ="email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "photo")
    private String attachId;
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private AttachEntity attachEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ProfileRole role = ProfileRole.USER;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProfileStatus status = ProfileStatus.NOT_ACTIVE;

    @Column(name = "visible")
    private boolean visible = true;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
