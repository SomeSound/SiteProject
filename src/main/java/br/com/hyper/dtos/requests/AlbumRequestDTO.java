package br.com.hyper.dtos.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class AlbumRequestDTO {

    private Long id;
    @NotEmpty(message = "Invalid name, can not be empty")
    private String name;

    private String albumImage;

    private Date releaseDate;

    private String recordId;
}
