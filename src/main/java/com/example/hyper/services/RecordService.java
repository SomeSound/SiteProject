package com.example.hyper.services;

import com.example.hyper.dtos.requests.RecordRequestDTO;
import com.example.hyper.dtos.responses.RecordResponseDTO;
import com.example.hyper.dtos.responses.pages.RecordPageReponseDTO;
import org.springframework.data.domain.Pageable;

public interface RecordService {

    public RecordResponseDTO save(RecordRequestDTO record);

    public RecordPageReponseDTO find(String name, Pageable pageable);

    public RecordResponseDTO update(Long id, RecordRequestDTO record);

    public void delete(Long id);

}
