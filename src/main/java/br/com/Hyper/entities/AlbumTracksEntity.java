package br.com.hyper.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "ALBUM_TRACKS")
public class AlbumTracksEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALBUM_TRACKS_SEQ")
    @SequenceGenerator(name = "ALBUM_TRACKS_SEQ", sequenceName = "ALBUM_TRACKS_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ALBUM")
    private AlbumEntity album;

    @ManyToOne
    @JoinColumn(name = "TRACK")
    private TrackEntity track;

}
