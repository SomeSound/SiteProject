package com.example.hyper.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMER")
@EqualsAndHashCode(callSuper = false)
public class CustomerEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ")
    @SequenceGenerator(name = "CUSTOMER_SEQ",sequenceName = "CUSTOMER_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "CUSTOMER_ID")
    private String customerId;

    @Column(name = "AVATAR")
    private String avatar;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "COUNTRY", nullable = false)
    private String country;

    @Column(name = "BIRTH_DATE", nullable = false)
    private String birthDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IS_ARTIST")
    private boolean isArtist;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "SUBSCRIPTION_ID")
    private SubscriptionEntity subscription;

//    @OneToMany
//    @JoinColumn(name = "CUSTOMER_ORDERS")
//    private List<CartEntity> cartList;

//    @OneToMany
//    @JoinColumn(name = "CUSTOMER_ORDERS")
//    @PrimaryKeyJoinColumn(name = "ORDER_ID")
//    private List<OrderEntity> orderList;
}
