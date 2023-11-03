package com.example.SiteProject.SiteProject.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
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

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DURATION", nullable = false)
    private Double duration;

    @Column(name = "GENRE", nullable = false)
    private String genre;

//    @Column(name = "ARTIST") // CRIAR FOREIGN-KEY APONTANDO PARA ARTISTA
//    private ArtistEntity Artist;
}
