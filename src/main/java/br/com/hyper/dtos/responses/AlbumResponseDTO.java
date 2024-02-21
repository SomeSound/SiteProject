package br.com.hyper.dtos.responses;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class AlbumResponseDTO {
    private Long id;
    private String name;
    private String image;
    private Long recordId;
    private ZonedDateTime releaseDate;
    private ZonedDateTime createdDate;
    private ZonedDateTime lastModifiedDate;
    private List<TrackResponseDTO> trackList;
}
