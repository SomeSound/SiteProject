package br.com.hyper.dtos.responses;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class RecordResponseDTO {

    private Long id;
    private String name;
    private String country;
    private String description;
}
