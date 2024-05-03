package br.com.hyper.entities;

import br.com.hyper.enums.StatusEmail;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMAIL")
@EqualsAndHashCode(callSuper = false)
public class EmailEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMAIL_SEQ")
    @SequenceGenerator(name = "EMAIL_SEQ", sequenceName = "EMAIL_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "OWNER", nullable = false)
    private String owner;

    @Column(name = "EMAIL_FROM", nullable = false)
    private String emailFrom;

    @Column(name = "SUBJECT", nullable = false)
    private String subject;

    @Column(name = "EMAIL_TO", nullable = false)
    private String emailTo;

    @Column(name = "TEXT", nullable = false)
    private String text;

    @Column(name = "SEND_DATE", nullable = false)
    private ZonedDateTime sendDate;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "STATUS", nullable = false)
    private StatusEmail status;
}