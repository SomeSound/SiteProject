package br.com.hyper.services;

import br.com.hyper.proxys.SpotifyProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpotifyServiceImpl implements SpotifyService {

    @Autowired
    private final SpotifyProxy spotifyProxy;

    @Override
    public Paging<Track> findTracksByName(String name) {
        try {
            return spotifyProxy.findTrackByName(name);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Paging<AlbumSimplified> findAlbumsByName(String name) {
        try {
            return spotifyProxy.findAlbumByName(name);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Paging<Artist> findArtistsByName(String name) {
        try {
            return spotifyProxy.findArtistsByName(name);
        }catch (Exception e){
            return null;
        }
    }
}
