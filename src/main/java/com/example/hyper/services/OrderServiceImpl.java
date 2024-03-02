package com.example.hyper.services;

import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.requests.OrderRequestDTO;
import com.example.hyper.dtos.responses.OrderResponseDTO;
import com.example.hyper.dtos.responses.pages.OrderPageResponseDTO;
import com.example.hyper.entities.OrderEntity;
import com.example.hyper.exceptions.InvalidOrderDataException;
import com.example.hyper.exceptions.OrderNotFoundException;
import com.example.hyper.repositories.OrderRepository;
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
public class OrderServiceImpl implements OrderService{

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public OrderResponseDTO save(OrderRequestDTO order) {

        OrderEntity orderEntity;

        try {
            orderEntity = modelMapper.map(order, OrderEntity.class);

            orderEntity = orderRepository.save(orderEntity);

            return modelMapper.map(orderEntity, OrderResponseDTO.class);

        } catch (DataIntegrityViolationException e) {
            throw new InvalidOrderDataException(ErrorCodes.INVALID_ORDER_ERROR,
                    ErrorCodes.INVALID_ORDER_ERROR.getMessage());
        }
    }

    @Override
    public OrderPageResponseDTO find(List<String> Id, Pageable pageable) {
        Page<OrderEntity> orderEntities;

        if(Id != null){
            orderEntities = orderRepository.findByName(Id, pageable);
        } else {
            orderEntities = orderRepository.findAll(pageable);
        }
        return modelMapper.map(orderEntities, OrderPageResponseDTO.class);
    }

    @Override
    public OrderResponseDTO update(Long id, OrderRequestDTO order) {
        OrderEntity orderCurrent = findByIdOrThrowOrderDataNotFoundException(id);

        orderCurrent.setId(order.getId());

        orderRepository.save(orderCurrent);

        return modelMapper.map(orderCurrent, OrderResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        OrderEntity orderCurrent = findByIdOrThrowOrderDataNotFoundException(id);

        orderRepository.delete(orderCurrent);
    }
    private OrderEntity findByIdOrThrowOrderDataNotFoundException(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }
}
