package br.com.hyper.dtos.responses.pages;

import br.com.hyper.dtos.responses.ReviewResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ReviewPageResponseDTO {
    private boolean last;

    private boolean first;

    private Integer totalElements;

    private Integer totalPages;

    private Integer size;

    private Integer numberOfElements;

    @JsonProperty("page")
    private Integer number;

    @JsonProperty("reviews")
    private List<ReviewResponseDTO> content;
}
