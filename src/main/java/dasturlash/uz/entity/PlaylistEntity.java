package dasturlash.uz.entity;

import dasturlash.uz.enums.ChannelStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "playlist")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;

    @Column(name = "channel_id")
    private String channel_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", insertable = false, updatable = false)
    private ChannelEntity channel;

    @Column(name = "name")
    private String name;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ChannelStatus status;

    @Column(name = "order_num")
    private String order_num;

    @Column(name = "visible")
    private boolean visible = true;

    @Column(name = "created_date")
    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    private String createdDate;
}
