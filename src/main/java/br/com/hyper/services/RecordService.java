package br.com.hyper.services;

import br.com.hyper.dtos.requests.RecordRequestDTO;
import br.com.hyper.dtos.responses.RecordResponseDTO;
import br.com.hyper.dtos.responses.pages.RecordPageReponseDTO;
import org.springframework.data.domain.Pageable;

public interface RecordService {

    RecordResponseDTO save(RecordRequestDTO record);

    RecordPageReponseDTO find(String name, Pageable pageable);

    RecordResponseDTO update(Long id, RecordRequestDTO record);

    void delete(Long id);

}
