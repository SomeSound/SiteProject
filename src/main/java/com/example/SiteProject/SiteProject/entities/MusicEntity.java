package com.example.SiteProject.SiteProject.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MUSIC")
@EqualsAndHashCode(of = "id")
public class MusicEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUSIC_SEQ")
    @SequenceGenerator(name = "MUSIC_SEQ", sequenceName = "MUSIC_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DURATION")
    private Double duration;

    @Column(name = "GENRE")
    private String genre;

//    @Column(name = "ARTIST") // CRIAR FOREIGN-KEY APONTANDO PARA ARTISTA
//    private ArtistEntity Artist;
}
