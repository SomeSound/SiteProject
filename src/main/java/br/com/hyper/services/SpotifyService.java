package br.com.hyper.services;

import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;

public interface SpotifyService {
    Paging<Track> findTracksByName(String name);

    Paging<AlbumSimplified> findAlbumsByName(String name);

    Paging<Artist> findArtistsByName(String name);

}
