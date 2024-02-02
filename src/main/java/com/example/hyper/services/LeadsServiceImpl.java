package com.example.hyper.services;

import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.requests.LeadsRequestDTO;
import com.example.hyper.dtos.responses.LeadsResponseDTO;
import com.example.hyper.dtos.responses.pages.LeadsPageResponseDTO;
import com.example.hyper.entities.LeadsEntity;
import com.example.hyper.exceptions.InvalidLeadsDataException;
import com.example.hyper.exceptions.LeadsNotFoundException;
import com.example.hyper.repositories.LeadsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LeadsServiceImpl implements LeadsService {

    @Autowired
    private LeadsRepository leadRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LeadsResponseDTO save(LeadsRequestDTO lead) {

        LeadsEntity leadEntity;
        try {
            leadEntity = modelMapper.map(lead, LeadsEntity.class);

            leadEntity = leadRepository.save(leadEntity);

            return modelMapper.map(leadEntity, LeadsResponseDTO.class);

        } catch (DataIntegrityViolationException e) {
            throw new InvalidLeadsDataException(ErrorCodes.INVALID_COLLECTION_ERROR,
                    ErrorCodes.INVALID_COLLECTION_ERROR.getMessage());
        }
    }

    @Override
    public LeadsPageResponseDTO find(List<String> names, Pageable pageable) {

        Page<LeadsEntity> leadsEntities;

        if(names != null){
            leadsEntities = leadRepository.findByName(names, pageable);
        } else {
            leadsEntities = leadRepository.findAll(pageable);
        }
        return modelMapper.map(leadsEntities, LeadsPageResponseDTO.class);
    }


    @Override
    public LeadsResponseDTO update(Long id, LeadsRequestDTO lead) {
        LeadsEntity leadCurrent = findByIdOrThrowLeadsDataNotFoundException(id);

        leadCurrent.setId(lead.getId());

        leadRepository.save(leadCurrent);

        return modelMapper.map(leadCurrent, LeadsResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        LeadsEntity leadCurrent = findByIdOrThrowLeadsDataNotFoundException(id);

        LeadsResponseDTO response = modelMapper.map(leadCurrent, LeadsResponseDTO.class);
        leadRepository.delete(leadCurrent);
    }
    private LeadsEntity findByIdOrThrowLeadsDataNotFoundException(Long id) {
        return leadRepository.findById(id).orElseThrow(
                () -> new LeadsNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }
}
