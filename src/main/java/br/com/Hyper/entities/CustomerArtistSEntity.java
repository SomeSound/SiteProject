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
@Table(name = "CUSTOMER_ARTISTS")
public class CustomerArtistsEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_ARTIST_SEQ")
    @SequenceGenerator(name = "CUSTOMER_ARTIST_SEQ", sequenceName = "CUSTOMER_ARTIST_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "ARTIST")
    private ArtistEntity artist;

}
