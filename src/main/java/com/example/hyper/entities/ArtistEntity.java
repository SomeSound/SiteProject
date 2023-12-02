package com.example.hyper.entities;

import com.example.hyper.repositories.MusicRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Indexed;

import java.io.Serializable;
import java.util.List;

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
