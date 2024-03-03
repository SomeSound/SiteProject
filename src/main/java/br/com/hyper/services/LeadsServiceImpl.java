package br.com.hyper.services;

import br.com.hyper.constants.ErrorCodes;
import br.com.hyper.dtos.requests.LeadsRequestDTO;
import br.com.hyper.dtos.responses.LeadsResponseDTO;
import br.com.hyper.dtos.responses.pages.LeadsPageResponseDTO;
import br.com.hyper.entities.LeadsEntity;
import br.com.hyper.exceptions.InvalidLeadsDataException;
import br.com.hyper.exceptions.LeadsNotFoundException;
import br.com.hyper.repositories.LeadsRepository;
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
            throw new InvalidLeadsDataException(ErrorCodes.DUPLICATED_DATA,
                    ErrorCodes.DUPLICATED_DATA.getMessage());
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
