package br.com.hyper.services;

import br.com.hyper.dtos.requests.LeadsRequestDTO;
import br.com.hyper.dtos.responses.LeadsResponseDTO;
import br.com.hyper.dtos.responses.pages.LeadsPageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LeadsService {

    public LeadsResponseDTO save(LeadsRequestDTO lead);

    public LeadsPageResponseDTO find(List<String> names, Pageable pageable);

    public LeadsResponseDTO update(Long id, LeadsRequestDTO lead);

    public void delete(Long id);
}
