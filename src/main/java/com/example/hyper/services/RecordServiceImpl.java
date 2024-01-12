package com.example.hyper.services;

import com.example.hyper.dtos.requests.RecordRequestDTO;
import com.example.hyper.dtos.responses.RecordResponseDTO;
import com.example.hyper.dtos.responses.pages.RecordPageReponseDTO;
import org.springframework.data.domain.Pageable;

public class RecordServiceImpl implements RecordService{
    @Override
    public RecordResponseDTO save(RecordRequestDTO record) {
        return null;
    }

    @Override
    public RecordPageReponseDTO find(String name, Pageable pageable) {
        return null;
    }

    @Override
    public RecordResponseDTO update(Long id, RecordRequestDTO record) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
