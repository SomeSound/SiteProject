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
@Table(name = "ARTIST_ALBUMS")
public class ArtistAlbumsEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTIST_ALBUMS_SEQ")
    @SequenceGenerator(name = "ARTIST_ALBUMS_SEQ", sequenceName = "ARTIST_ALBUMS_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ARTIST")
    private ArtistEntity artist;

    @ManyToOne
    @JoinColumn(name = "ALBUM")
    private AlbumEntity album;
}
