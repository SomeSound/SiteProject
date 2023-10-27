package com.example.SiteProject.SiteProject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MUSIC")
@EqualsAndHashCode(callSuper = true)
public class MusicEtity extends BaseEntity {

    private Long id;

    private String name;

    private long duration;

    private ArtistEntity Artist;
}
