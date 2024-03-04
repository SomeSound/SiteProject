package br.com.hyper.services;

import br.com.hyper.dtos.requests.CollectionRequestDTO;
import br.com.hyper.dtos.responses.CollectionResponseDTO;
import br.com.hyper.dtos.responses.pages.CollectionPageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface CollectionService {

    CollectionResponseDTO save(CollectionRequestDTO collection);

    CollectionPageResponseDTO find(String collection, Pageable pageable);

    CollectionResponseDTO update(Long id, CollectionRequestDTO collection);
    void delete(Long id);

}
