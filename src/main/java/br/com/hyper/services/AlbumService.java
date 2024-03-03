package br.com.hyper.services;

import br.com.hyper.dtos.requests.AlbumRequestDTO;
import br.com.hyper.dtos.responses.AlbumResponseDTO;
import br.com.hyper.dtos.responses.pages.AlbumPageReponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlbumService {

    public AlbumResponseDTO save(AlbumRequestDTO playlist);

    public AlbumPageReponseDTO find(List<String> name, Pageable pageable);

    public AlbumResponseDTO update(Long id, AlbumRequestDTO album);

    public void delete(Long id);
}
