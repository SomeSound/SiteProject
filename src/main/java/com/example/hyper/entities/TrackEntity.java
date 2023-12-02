package com.example.hyper.entities;

import com.example.hyper.enums.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRACK")
public class TrackEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUSIC_SEQ")
    @SequenceGenerator(name = "MUSIC_SEQ", sequenceName = "MUSIC_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DURATION", nullable = false)
    private Double duration;

    @Column(name = "GENRE", nullable = false)
    private Genre genre;

//    @Column(name = "REVIEW")
//    private ReviewEntity reviewEntity;

//    @Column(name = "ARTIST") // CRIAR FOREIGN-KEY APONTANDO PARA ARTISTA
//    private ArtistEntity Artist;
}
