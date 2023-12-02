package com.example.SiteProject.SiteProject.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ",sequenceName = "USER_SEQ", allocationSize = 1)
    private Long id;

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

}
