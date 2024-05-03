package br.com.hyper.entities;

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
@Table(name = "ORDER_TABLE")
@EqualsAndHashCode(callSuper = false)
public class OrderEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ")
    @SequenceGenerator(name = "ORDER_SEQ", sequenceName = "ORDER_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER", nullable = false)
    private CustomerEntity customer;

    @Column(name = "TOTAL_ITEMS", nullable = false)
    private int totalItems;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private BigDecimal totalPrice;
}