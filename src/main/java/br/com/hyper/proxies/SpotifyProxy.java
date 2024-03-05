package br.com.hyper.proxies;

import com.neovisionaries.i18n.CountryCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpotifyProxy {

    private static final String CLIENT_ID = "";
    private static final String CLIENT_SECRET = "";

    private static final SpotifyApi spotifyAPI = new SpotifyApi.Builder()
            .setClientId(CLIENT_ID)
            .setClientSecret(CLIENT_SECRET)
            .build();

    private static final ClientCredentialsRequest clientCredentialsRequest = spotifyAPI.clientCredentials()
            .build();

    public Paging<Track> findTrackByName(String title) {

        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            spotifyAPI.setAccessToken(clientCredentials.getAccessToken());

            final SearchTracksRequest searchTracksRequest = spotifyAPI.searchTracks(title)
                    .market(CountryCode.BR)
                    .limit(5)
                    .offset(0)
                    .build();

            return searchTracksRequest.execute();

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            return null;
        }
    }

    public Paging<AlbumSimplified> findAlbumByName(String title) {

        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            spotifyAPI.setAccessToken(clientCredentials.getAccessToken());

            final SearchAlbumsRequest searchAlbumsRequest = spotifyAPI.searchAlbums(title)
                    .market(CountryCode.BR)
                    .limit(5)
                    .offset(0)
                    .build();

            return searchAlbumsRequest.execute();

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            return null;
        }
    }

    public Paging<Artist> findArtistsByName(String title) {

        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            spotifyAPI.setAccessToken(clientCredentials.getAccessToken());

            final SearchArtistsRequest searchArtistsRequest = spotifyAPI.searchArtists(title)
                    .market(CountryCode.BR)
                    .limit(5)
                    .offset(0)
                    .build();

            return searchArtistsRequest.execute();

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            return null;
        }
    }

}
