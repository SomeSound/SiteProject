package com.example.hyper.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ARTIST")
@EqualsAndHashCode(of = "id", callSuper = false)
public class ArtistEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTIST_SEQ")
    @SequenceGenerator(name = "ARTIST_SEQ", sequenceName = "ARTIST_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COUNTRY")
    private String country;

}
