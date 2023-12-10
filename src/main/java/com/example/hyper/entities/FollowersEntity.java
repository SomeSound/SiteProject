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
@Table(name = "FOLLOWERS")
@EqualsAndHashCode(callSuper = false)
public class FollowersEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FOLLOW_SEQ")
    @SequenceGenerator(name = "FOLLOW_SEQ", sequenceName = "FOLLOW_SEQ", allocationSize = 1)
    private Long id;

    @ManyToMany
    private List<CustomerEntity> users;
}
