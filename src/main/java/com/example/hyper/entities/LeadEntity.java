package com.example.hyper.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LEADS")
public class LeadEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEW_SEQ")
    @SequenceGenerator(name = "REVIEW_SEQ", sequenceName = "REVIEW_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private double name;

    @Column(name = "EMAIL")
    private String email;

}
