package br.com.hyper.services;

import br.com.hyper.dtos.requests.LeadsRequestDTO;
import br.com.hyper.dtos.responses.LeadsResponseDTO;
import br.com.hyper.dtos.responses.pages.LeadsPageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LeadsService {

    LeadsResponseDTO save(LeadsRequestDTO lead);

    LeadsPageResponseDTO find(List<String> emails, Pageable pageable);

    LeadsResponseDTO update(Long id, LeadsRequestDTO lead);

    void delete(Long id);
}
