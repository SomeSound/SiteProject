package com.example.hyper.entities;

import com.example.hyper.enums.StatusEmail;
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

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "EMAIL_FROM")
    private String emailFrom;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "EMAIL_TO")
    private String emailTo;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "SEND_DATE")
    private ZonedDateTime sendDate;

    @Column(name = "STATUS")
    private StatusEmail status;


}





