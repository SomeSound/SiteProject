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
public class ArtistEntity extends UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTIST_SEQ")
    @SequenceGenerator(name = "ARTIST_SEQ", sequenceName = "ARTIST_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "CREDITS")
    private double credits;

}
