package com.example.hyper.services;

import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.requests.CartRequestDTO;
import com.example.hyper.dtos.responses.CartResponseDTO;
import com.example.hyper.dtos.responses.pages.CartPageResponseDTO;
import com.example.hyper.entities.CartEntity;
import com.example.hyper.exceptions.CartNotFoundException;
import com.example.hyper.exceptions.InvalidCartDataException;
import com.example.hyper.repositories.CartRepository;
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
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CartResponseDTO save(CartRequestDTO cart) {

        CartEntity cartEntity;
        try {
            cartEntity = modelMapper.map(cart, CartEntity.class);

            cartEntity = cartRepository.save(cartEntity);

            return modelMapper.map(cartEntity, CartResponseDTO.class);

        } catch (DataIntegrityViolationException e) {
            throw new InvalidCartDataException(ErrorCodes.INVALID_COLLECTION_ERROR,
                    ErrorCodes.INVALID_COLLECTION_ERROR.getMessage());
        }
    }

    @Override
    public CartPageResponseDTO find(List<String> Id, Pageable pageable) {

        Page<CartEntity> cartEntities;

        if(Id != null){
            cartEntities = cartRepository.findByName(Id, pageable);
        } else {
            cartEntities = cartRepository.findAll(pageable);
        }
        return modelMapper.map(cartEntities, CartPageResponseDTO.class);
    }


    @Override
    public CartResponseDTO update(Long id, CartRequestDTO cart) {
        CartEntity cartCurrent = findByIdOrThrowCartDataNotFoundException(id);

        cartCurrent.setId(cart.getId());

        cartRepository.save(cartCurrent);

        return modelMapper.map(cartCurrent, CartResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        CartEntity cartCurrent = findByIdOrThrowCartDataNotFoundException(id);

        CartResponseDTO response = modelMapper.map(cartCurrent, CartResponseDTO.class);
        cartRepository.delete(cartCurrent);
    }
    private CartEntity findByIdOrThrowCartDataNotFoundException(Long id) {
        return cartRepository.findById(id).orElseThrow(
                () -> new CartNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }
}
