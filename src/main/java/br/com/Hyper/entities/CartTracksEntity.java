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
@Table(name = "CART_TRACKS")
public class CartTracksEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CART_TRACKS_SEQ")
    @SequenceGenerator(name = "CART_TRACKS_SEQ", sequenceName = "CART_TRACKS_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TRACK")
    private TrackEntity track;

    @ManyToOne
    @JoinColumn(name = "CART")
    private CartEntity cart;

}
