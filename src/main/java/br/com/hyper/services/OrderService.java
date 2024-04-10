package br.com.hyper.services;

import br.com.hyper.dtos.requests.OrderRequestDTO;
import br.com.hyper.dtos.responses.OrderResponseDTO;
import br.com.hyper.dtos.responses.pages.OrderPageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDTO save(OrderRequestDTO order);

    OrderPageResponseDTO find(String email, Pageable pageable);

    OrderResponseDTO update(Long id, OrderRequestDTO order);

    void delete(Long id);
}
