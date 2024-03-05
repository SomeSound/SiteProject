package br.com.hyper.dtos.responses.pages;

import br.com.hyper.dtos.responses.CartResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CartPageResponseDTO {
    private boolean last;

    private boolean first;

    private Integer totalElements;

    private Integer totalPages;

    private Integer size;

    private Integer numberOfElements;

    @JsonProperty("page")
    private Integer number;

    @JsonProperty("CollectionDataList")
    private List<CartResponseDTO> content;
}
