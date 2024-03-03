package br.com.hyper.dtos.responses.pages;

import br.com.hyper.dtos.requests.LeadsRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LeadsPageResponseDTO {

    private boolean last;

    private boolean first;

    private Integer totalElements;

    private Integer totalPages;

    private Integer size;

    private Integer numberOfElements;

    @JsonProperty("page")
    private Integer number;

    @JsonProperty("userDataList")
    private List<LeadsRequestDTO> content;
}