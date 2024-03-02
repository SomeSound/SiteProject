package com.example.hyper.services;

import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.requests.FollowRequestDTO;
import com.example.hyper.dtos.responses.FollowResponseDTO;
import com.example.hyper.dtos.responses.pages.FollowPageResponseDTO;
import com.example.hyper.entities.FollowEntity;
import com.example.hyper.exceptions.FollowNotFoundException;
import com.example.hyper.exceptions.InvalidFollowDataException;
import com.example.hyper.repositories.FollowRepository;
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
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FollowResponseDTO save(FollowRequestDTO follow) {

        FollowEntity followEntity;
        try {
            followEntity = modelMapper.map(follow, FollowEntity.class);

            followEntity = followRepository.save(followEntity);

            return modelMapper.map(followEntity, FollowResponseDTO.class);

        } catch (DataIntegrityViolationException e) {
            throw new InvalidFollowDataException(ErrorCodes.DUPLICATED_DATA,
                    ErrorCodes.DUPLICATED_DATA.getMessage());
        }
    }

    @Override
    public FollowPageResponseDTO find(List<String> Id, Pageable pageable) {

        Page<FollowEntity> followEntities;

        if(Id != null){
            followEntities = followRepository.findByName(Id, pageable);
        } else {
            followEntities = followRepository.findAll(pageable);
        }
        return modelMapper.map(followEntities, FollowPageResponseDTO.class);
    }


    @Override
    public FollowResponseDTO update(Long id, FollowRequestDTO cart) {
        FollowEntity followCurrent = findByIdOrThrowFollowDataNotFoundException(id);

        followCurrent.setId(cart.getId());

        followRepository.save(followCurrent);

        return modelMapper.map(followCurrent, FollowResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        FollowEntity followCurrent = findByIdOrThrowFollowDataNotFoundException(id);

        FollowResponseDTO response = modelMapper.map(followCurrent, FollowResponseDTO.class);
        followRepository.delete(followCurrent);
    }
    private FollowEntity findByIdOrThrowFollowDataNotFoundException(Long id) {
        return followRepository.findById(id).orElseThrow(
                () -> new FollowNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }
}
