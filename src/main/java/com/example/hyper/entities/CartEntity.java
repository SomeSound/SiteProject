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
@Table(name = "CART")
@EqualsAndHashCode(callSuper = false)
public class CartEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CART_SEQ")
    @SequenceGenerator(name = "CART_SEQ", sequenceName = "CART_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;

    @ManyToMany
    private List<UserEntity> users;

    @OneToMany
    private List<TrackEntity> tracks;
}
