package com.example.SiteProject.SiteProject.services;

import com.example.SiteProject.SiteProject.constants.ErrorCodes;
import com.example.SiteProject.SiteProject.dtos.UserDTO;
import com.example.SiteProject.SiteProject.dtos.responses.UserPageResponseDTO;
import com.example.SiteProject.SiteProject.dtos.responses.UserResponseDTO;
import com.example.SiteProject.SiteProject.entities.UserEntity;
import com.example.SiteProject.SiteProject.exceptions.ArtistNotFoundException;
import com.example.SiteProject.SiteProject.exceptions.InvalidUserDataException;
import com.example.SiteProject.SiteProject.repositories.UserRepository;
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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public UserResponseDTO save(UserDTO user) {
        UserEntity userEntity;
        try {
            userEntity = modelMapper.map(user, UserEntity.class);

            userEntity = userRepository.save(userEntity);

            return modelMapper.map(userEntity, UserResponseDTO.class);
        }catch (DataIntegrityViolationException e){
            throw new InvalidUserDataException(ErrorCodes.INVALID_USER_ERROR,
                    ErrorCodes.INVALID_USER_ERROR.getMessage());
        }
    }

    @Override
    public UserPageResponseDTO find(List<String> names, Pageable pageable) {

        Page<UserEntity> userEntities;

        if(names != null){
            userEntities = userRepository.findByName(names, pageable);
        } else {
            userEntities = userRepository.findAll(pageable);
        }
        return modelMapper.map(userEntities, UserPageResponseDTO.class);
    }

    @Override
    public UserResponseDTO update(Long id, UserDTO user) {
        UserEntity userCurrent = findByIdOrThrowUserDataNotFoundException(id);

        userCurrent.setName(user.getName());
        userCurrent.setUsername(user.getUsername());

        userRepository.save(userCurrent);

        return modelMapper.map(userCurrent, UserResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        UserEntity userCurrent = findByIdOrThrowUserDataNotFoundException(id);

        UserResponseDTO response = modelMapper.map(userCurrent, UserResponseDTO.class);
        userRepository.delete(userCurrent);
    }

    private UserEntity findByIdOrThrowUserDataNotFoundException(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ArtistNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }

}
