package com.example.hyper.entities;

import com.example.hyper.enums.Genre;
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
@Table(name = "TRACK")
@EqualsAndHashCode(callSuper = false)
public class TrackEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRACK_SEQ")
    @SequenceGenerator(name = "TRACK_SEQ", sequenceName = "TRACK_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DURATION", nullable = false)
    private Double duration;

    @Column(name = "GENRE", nullable = false)
    private Genre genre;

    @OneToMany
    private List<ReviewEntity> reviews;

    @ManyToMany
    private List<PlaylistEntity> playlist;

    @ManyToOne
    private AlbumEntity album;
}
