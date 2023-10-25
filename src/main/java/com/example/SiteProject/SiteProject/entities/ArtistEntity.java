package com.example.SiteProject.SiteProject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Data
@Getter
@Setter
//@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ARTIST")
@EqualsAndHashCode(callSuper = true)
public class ArtistEntity extends BaseEntity {

    private Long id;

    private String name;

    private String country;

}
