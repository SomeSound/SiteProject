package br.com.hyper.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

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

    @Column(name = "CUSTOMER_ID", nullable = false, unique = true)
    private String customerId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "COUNTRY", nullable = false)
    private String country;

    @Column(name = "BIRTH_DATE", nullable = false)
    private String birthDate;

    @Column(name = "AVATAR")
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "_OPTION")
    private SubscriptionEntity subscription;

}
