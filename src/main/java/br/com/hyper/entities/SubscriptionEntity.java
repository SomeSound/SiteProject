package br.com.hyper.entities;

import br.com.hyper.enums.SubscriptionOption;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "_OPTION")
    private SubscriptionOption option;

    @Column(name = "PRICE")
    private BigDecimal price;
}
