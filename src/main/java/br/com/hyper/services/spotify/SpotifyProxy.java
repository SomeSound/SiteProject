package br.com.hyper.services.spotify;

import br.com.hyper.dtos.responses.TrackResponseDTO;
import com.neovisionaries.i18n.CountryCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpotifyProxy {

    @Autowired
    private final ModelMapper modelMapper;

    private static final String CLIENT_ID = "6761012164494ab5a727c41fa9028bdb";
    private static final String CLIENT_SECRET = "0331be938f7444d485b7003cbfee5c8d";
    private static final SpotifyApi spotifyAPI = new SpotifyApi.Builder()
            .setClientId(CLIENT_ID)
            .setClientSecret(CLIENT_SECRET)
            .build();

    private static final ClientCredentialsRequest clientCredentialsRequest = spotifyAPI.clientCredentials()
            .build();
    public Paging<Track> searchTrack(String title) {

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

}
