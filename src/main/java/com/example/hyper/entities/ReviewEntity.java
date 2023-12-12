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
@Table(name = "REVIEW")
@EqualsAndHashCode(callSuper = false)
public class ReviewEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEW_SEQ")
    @SequenceGenerator(name = "REVIEW_SEQ", sequenceName = "REVIEW_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "SCORE")
    private float score;

    @Column(name = "CUSTOMER_ID")
    private CustomerEntity customerId;

    @Column(name = "TRACK_ID")
    private TrackEntity trackId;

    //adicionar ID do "Reviewer"

}
