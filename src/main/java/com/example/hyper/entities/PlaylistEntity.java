package com.example.hyper.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PLAYLIST")
@EqualsAndHashCode(of = "id")
public class PlaylistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAYLIST_SEQ")
    @SequenceGenerator(name = "PLAYLIST_SEQ", sequenceName = "PLAYLIST_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

//    @Column(name = "musics", nullable = false)
//    private List<MusicEntity> musics;
}
