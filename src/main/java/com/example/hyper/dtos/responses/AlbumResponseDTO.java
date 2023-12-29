package com.example.hyper.dtos.responses;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class AlbumResponseDTO {
    private Long id;
    private String name;
    private String image;
    private Long recordId;
    private Long customerId;
    private ZonedDateTime releaseDate;
    private ZonedDateTime createdDate;
    private ZonedDateTime lastModifiedDate;
}
