package br.com.hyper.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PLAYLIST")
@EqualsAndHashCode(callSuper = false)
public class PlaylistEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAYLIST_SEQ")
    @SequenceGenerator(name = "PLAYLIST_SEQ", sequenceName = "PLAYLIST_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @JoinColumn(name = "CUSTOMER")
    private CustomerEntity customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "playlist_track",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "track_id")
    )
    private List<TrackEntity> tracks;
}