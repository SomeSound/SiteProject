package br.com.hyper.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RECORD")
@EqualsAndHashCode(callSuper = false)
public class RecordEntity extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECORD_SEQ")
    @SequenceGenerator(name = "RECORD_SEQ", sequenceName = "RECORD_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recordId")
    private List<AlbumEntity> albumList;
}
