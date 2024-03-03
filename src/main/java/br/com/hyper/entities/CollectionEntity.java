package br.com.hyper.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "COLLECTION")
@EqualsAndHashCode(callSuper = false)
public class CollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COLLECTION_SEQ")
    @SequenceGenerator(name = "ARTIST_SEQ", sequenceName = "COLLECTION_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    //@NotEmpty
    //private List<playlist> playlists
}