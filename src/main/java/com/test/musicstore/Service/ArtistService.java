package com.test.musicstore.Service;
import com.test.musicstore.POJO.Request.SaveArtistRequest;
import com.test.musicstore.POJO.Response.GetAllArtistsResponse;
import com.test.musicstore.POJO.Response.ArtistResponse;

import java.util.Optional;
import java.util.UUID;

public interface ArtistService {
    ArtistResponse saveArtist(SaveArtistRequest request);
    ArtistResponse getArtist(UUID artistId);
    GetAllArtistsResponse getAllArtists();
    ArtistResponse updateArtist(UUID artistId, SaveArtistRequest saveArtistRequest);
    ArtistResponse deleteArtist(UUID artistId);
}
