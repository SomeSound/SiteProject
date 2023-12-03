package com.example.hyper.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ALBUM")
@EqualsAndHashCode(callSuper = false)
public class AlbumEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALBUM_SEQ")
    @SequenceGenerator(name = "ALBUM_SEQ", sequenceName = "ALBUM_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private double name;

    @Column(name = "TRACKS")
    private List<TrackEntity> tracks;

    @Column(name = "RECORD")
    private Record record;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "RELEASE_DATE")
    private ZonedDateTime releaseDate;
}
