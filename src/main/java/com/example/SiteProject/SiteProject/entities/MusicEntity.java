package com.example.SiteProject.SiteProject.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MUSIC")
@EqualsAndHashCode(callSuper = true)
public class MusicEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUSIC_SEQ")
    @SequenceGenerator(name = "MUSIC_SEQ", sequenceName = "MUSIC_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DURATION")
    private long duration;

//    @Column(name = "ARTIST") // CRIAR FOREIGN-KEY APONTANDO PARA ARTISTA
//    private ArtistEntity Artist;
}
