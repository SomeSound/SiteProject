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
@Table(name = "SUBSCRIPTION")
@EqualsAndHashCode(callSuper = false)
public class SubscriptionEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBSCRIPTION_SEQ")
    @SequenceGenerator(name = "SUBSCRIPTION_SEQ", sequenceName = "SUBSCRIPTION_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

//    @Enumerated(EnumType.STRING)
//    private SubscriptionOption option;

    @OneToMany
    private List<UserEntity> users;

    @Column(name = "PRICE")
    private float price;
}
