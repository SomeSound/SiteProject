package br.com.hyper.services;

import br.com.hyper.constants.ErrorCodes;
import br.com.hyper.dtos.requests.RecordRequestDTO;
import br.com.hyper.dtos.responses.RecordResponseDTO;
import br.com.hyper.dtos.responses.pages.RecordPageReponseDTO;
import br.com.hyper.entities.RecordEntity;
import br.com.hyper.exceptions.InvalidCustomerDataException;
import br.com.hyper.exceptions.RecordNotFoundException;
import br.com.hyper.repositories.RecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService{

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RecordResponseDTO save(RecordRequestDTO record)  {

        RecordEntity recordEntity;
        try{
            recordEntity = modelMapper.map(record, RecordEntity.class);

            recordEntity = recordRepository.save(recordEntity);

            return modelMapper.map(recordEntity, RecordResponseDTO.class);

        }catch (DataIntegrityViolationException e){
            throw new InvalidCustomerDataException(ErrorCodes.DUPLICATED_DATA,
                    ErrorCodes.DUPLICATED_DATA.getMessage());
        }
    }

    @Override
    public RecordPageReponseDTO find(String name, Pageable pageable)  {

        Page<RecordEntity> recordEntities;

        if(name != null){
            recordEntities = recordRepository.findByName(name, pageable);
        } else {
            recordEntities = recordRepository.findAll(pageable);
        }

        return modelMapper.map(recordEntities, RecordPageReponseDTO.class);
    }

    @Override
    public RecordResponseDTO update(Long id, RecordRequestDTO record) {

        RecordEntity recordCurrent = findByIdOrThrowRecordDataNotFoundException(id);

        recordCurrent.setName(record.getName());

        recordRepository.save(recordCurrent);

        return modelMapper.map(recordCurrent, RecordResponseDTO.class);
    }
    @Override
    public void delete(Long id)  {
        RecordEntity recordCurrent = findByIdOrThrowRecordDataNotFoundException(id);

        recordRepository.delete(recordCurrent);
    }
    private RecordEntity findByIdOrThrowRecordDataNotFoundException(Long id) {
        return recordRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }
}
