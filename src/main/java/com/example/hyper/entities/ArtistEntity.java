package com.example.hyper.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ARTIST")
@EqualsAndHashCode(callSuper = false)
public class ArtistEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTIST_SEQ")
    @SequenceGenerator(name = "ARTIST_SEQ", sequenceName = "ARTIST_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "CREDITS")
    private int credits;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @ManyToOne(fetch = FetchType.EAGER)
    private CustomerEntity customer;

    @OneToMany(fetch = FetchType.LAZY)
    private List<AlbumEntity> albums;

    @OneToMany(fetch = FetchType.LAZY)
    private List<CartEntity> carts;

    @Column(name = "DESCRIPTION")
    private String description;

}
