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
@Table(name = "RECORD_ALBUMS")
public class RecordAlbumsEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECORD_ALBUMS_SEQ")
    @SequenceGenerator(name = "RECORD_ALBUMS_SEQ", sequenceName = "RECORD_ALBUMS_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "RECORD")
    private RecordEntity record;

    @ManyToOne
    @JoinColumn(name = "ALBUM")
    private AlbumEntity album;
}
