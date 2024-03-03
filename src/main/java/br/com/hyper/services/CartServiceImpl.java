package br.com.hyper.services;

import br.com.hyper.constants.ErrorCodes;
import br.com.hyper.dtos.requests.CartRequestDTO;
import br.com.hyper.dtos.responses.CartResponseDTO;
import br.com.hyper.dtos.responses.pages.CartPageResponseDTO;
import br.com.hyper.entities.CartEntity;
import br.com.hyper.exceptions.CartNotFoundException;
import br.com.hyper.exceptions.InvalidCartDataException;
import br.com.hyper.repositories.CartRepository;
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
            throw new InvalidCartDataException(ErrorCodes.DUPLICATED_DATA,
                    ErrorCodes.DUPLICATED_DATA.getMessage());
        }
    }

    @Override
    public CartPageResponseDTO find(Long customerId, Pageable pageable) {

        Page<CartEntity> cartEntities;

        if(customerId != null){
            cartEntities = cartRepository.findByCustomerId(customerId, pageable);
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
