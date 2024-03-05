package br.com.hyper.dtos.responses.pages;

import br.com.hyper.dtos.responses.RecordResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RecordPageReponseDTO {
    private boolean last;

    private boolean first;

    private Integer totalElements;

    private Integer totalPages;

    private Integer size;

    private Integer numberOfElements;

    @JsonProperty("page")
    private Integer number;

    @JsonProperty("playlistDataList")
    private List<RecordResponseDTO> content;
}
