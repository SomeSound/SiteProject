package br.com.hyper.controllers;

import br.com.hyper.services.SpotifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SpotifyController {

    @Autowired
    private final SpotifyService spotifyService;

    @GetMapping(value = "/spotify/tracks")
    public ResponseEntity<Paging<Track>> findTracksByName(@RequestParam(value = "name") String name) {

        Paging<Track> response = spotifyService.findTracksByName(name);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/spotify/albums")
    public ResponseEntity<Paging<AlbumSimplified>> findAlbumsByName(@RequestParam(value = "name") String name) {

        Paging<AlbumSimplified> response = spotifyService.findAlbumsByName(name);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/spotify/artists")
    public ResponseEntity<Paging<Artist>> findArtistsByName(@RequestParam(value = "name") String name) {

        Paging<Artist> response = spotifyService.findArtistsByName(name);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
