package br.com.hyper.services;

import br.com.hyper.constants.ErrorCodes;
import br.com.hyper.dtos.requests.OrderRequestDTO;
import br.com.hyper.dtos.responses.OrderResponseDTO;
import br.com.hyper.dtos.responses.pages.OrderPageResponseDTO;
import br.com.hyper.entities.CustomerEntity;
import br.com.hyper.entities.OrderEntity;
import br.com.hyper.exceptions.CustomerNotFoundException;
import br.com.hyper.exceptions.InvalidOrderDataException;
import br.com.hyper.exceptions.OrderNotFoundException;
import br.com.hyper.repositories.CustomerRepository;
import br.com.hyper.repositories.OrderRepository;
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
public class OrderServiceImpl implements OrderService{

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final CustomerRepository customerRepository;

    @Override
    public OrderResponseDTO save(OrderRequestDTO order) {

        OrderEntity orderEntity;

        try {
            orderEntity = modelMapper.map(order, OrderEntity.class);

            orderEntity = orderRepository.save(orderEntity);

            return modelMapper.map(orderEntity, OrderResponseDTO.class);

        } catch (DataIntegrityViolationException e) {
            throw new InvalidOrderDataException(ErrorCodes.DUPLICATED_DATA,
                    ErrorCodes.DUPLICATED_DATA.getMessage());
        }
    }

    @Override
    public OrderPageResponseDTO find(String email, Pageable pageable) {
        Page<OrderEntity> orderEntities;

        CustomerEntity customer = findByEmailOrThrowUserDataNotFoundException(email);

        if(email != null){
            orderEntities = orderRepository.findByCustomer(customer, pageable);
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

    private CustomerEntity findByEmailOrThrowUserDataNotFoundException(String email) {
        return customerRepository.findByEmail(email).orElseThrow(
                () -> new CustomerNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }
}
