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
@Table(name = "PLAYLIST_TRACKS")
public class PlaylistTracksEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAYLIST_TRACKS_SEQ")
    @SequenceGenerator(name = "PLAYLIST_TRACKS_SEQ", sequenceName = "PLAYLIST_TRACKS_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TRACK")
    private TrackEntity track;

    @ManyToOne
    @JoinColumn(name = "PLAYLIST")
    private PlaylistEntity playlist;

}
