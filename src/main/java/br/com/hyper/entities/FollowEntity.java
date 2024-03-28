package br.com.hyper.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FOLLOWERS")
@EqualsAndHashCode(callSuper = false)
public class FollowEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FOLLOW_SEQ")
    @SequenceGenerator(name = "FOLLOW_SEQ", sequenceName = "FOLLOW_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "FOLLOWING_ID")
    private CustomerEntity followingId;

    @Column(name = "FOLLOW_DATE")
    private ZonedDateTime followDate;
}
