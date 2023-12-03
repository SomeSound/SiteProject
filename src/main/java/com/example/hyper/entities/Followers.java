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
@Table(name = "CART")
@EqualsAndHashCode(callSuper = false)
public class Followers extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FOLLOW_SEQ")
    @SequenceGenerator(name = "FOLLOW_SEQ", sequenceName = "FOLLOW_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "USER")
    private UserEntity user;

    @Column(name = "ARTIST")
    private ArtistEntity artist;
}
