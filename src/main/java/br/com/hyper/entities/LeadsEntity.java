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
@Table(name = "LEADS")
@EqualsAndHashCode(callSuper = false)
public class LeadsEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LEADS_SEQ")
    @SequenceGenerator(name = "LEADS_SEQ", sequenceName = "LEADS_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TEXT")
    private String text;

    private CustomerEntity customerId;

}
