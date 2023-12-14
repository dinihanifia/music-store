package com.test.musicstore.Service.Impl;
import com.test.musicstore.Config.MessageConstant;
import com.test.musicstore.POJO.Entity.Artist;
import com.test.musicstore.POJO.Request.GetArtistRequest;
import com.test.musicstore.POJO.Request.SaveArtistRequest;
import com.test.musicstore.POJO.Response.AlbumResponse;
import com.test.musicstore.POJO.Response.GetAllArtistsResponse;
import com.test.musicstore.POJO.Response.ArtistResponse;
import com.test.musicstore.Repository.ArtistRepository;
import com.test.musicstore.Service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public ArtistResponse saveArtist(SaveArtistRequest request) {
        ArtistResponse artistResponse = new ArtistResponse();
        artistResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
        artistResponse.setResponseMessage(MessageConstant.BAD_REQUEST_MESSAGE);
        try{
            Artist artist = new Artist();
            artist.setArtistName(request.getArtistName());
            artist.setArtistCountry(request.getArtistCountry());
            var saveArtists = artistRepository.save(artist);
            return ArtistResponse.builder()
                    .responseCode(HttpStatus.OK.value())
                    .responseMessage(MessageConstant.SUCCESS_MESSAGE)
                    .artist(saveArtists)
                    .build();
        } catch (Exception e) {
            artistResponse.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            artistResponse.setResponseMessage(MessageConstant.ERROR_MESSAGE);
        }
        return artistResponse;
    }

    @Override
    public ArtistResponse getArtist(UUID artistId) {
        ArtistResponse artistResponse = new ArtistResponse();
        artistResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
        artistResponse.setResponseMessage(MessageConstant.BAD_REQUEST_MESSAGE);
        try {
            Optional<Artist> getArtist = artistRepository.findById(artistId);
            getArtist.ifPresentOrElse(
                    artist -> {
                        artistResponse.setResponseCode(HttpStatus.OK.value());
                        artistResponse.setResponseMessage(MessageConstant.SUCCESS_MESSAGE);
                        artistResponse.setArtist(artist);
                    },
                    () -> {
                        artistResponse.setResponseCode(HttpStatus.NOT_FOUND.value());
                        artistResponse.setResponseMessage(MessageConstant.NOT_FOUND_MESSAGE);
                    });
        } catch (Exception e) {
            artistResponse.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            artistResponse.setResponseMessage(MessageConstant.ERROR_MESSAGE);
        }
        return artistResponse;
    }

    @Override
    public GetAllArtistsResponse getAllArtists() {
        GetAllArtistsResponse getAllArtistsResponse = new GetAllArtistsResponse();
        getAllArtistsResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
        getAllArtistsResponse.setResponseMessage(MessageConstant.BAD_REQUEST_MESSAGE);
        try{
            var existArtist = artistRepository.findAll();
            List<Artist> artistList = existArtist.stream().collect(Collectors.toList());
            return GetAllArtistsResponse.builder()
                    .responseCode(HttpStatus.OK.value())
                    .responseMessage(MessageConstant.SUCCESS_MESSAGE)
                    .artistLists(artistList)
                    .build();
        } catch (Exception e){
            getAllArtistsResponse.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            getAllArtistsResponse.setResponseMessage(MessageConstant.ERROR_MESSAGE);
        }
        return getAllArtistsResponse;
    }

    @Override
    public ArtistResponse updateArtist(UUID artistId, SaveArtistRequest saveArtistRequest) {
        ArtistResponse response = new ArtistResponse();
        response.setResponseCode(HttpStatus.BAD_REQUEST.value());
        response.setResponseMessage(MessageConstant.BAD_REQUEST_MESSAGE);
        try{
            var getOneArtist = getArtist(artistId);
            if(getOneArtist.getResponseCode()==HttpStatus.OK.value()){
                var updateArtist = getOneArtist.getArtist();
                updateArtist.setArtistName(saveArtistRequest.getArtistName());
                updateArtist.setArtistCountry(saveArtistRequest.getArtistCountry());
                artistRepository.save(updateArtist);
                return ArtistResponse.builder()
                        .responseCode(HttpStatus.OK.value())
                        .responseMessage(MessageConstant.SUCCESS_MESSAGE)
                        .artist(updateArtist)
                        .build();
            }
            return getOneArtist;
        } catch (Exception e) {
            response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setResponseMessage(MessageConstant.ERROR_MESSAGE);
        }
        return response;
    }

    @Override
    public ArtistResponse deleteArtist(UUID artistId) {
        ArtistResponse response = new ArtistResponse();
        response.setResponseCode(HttpStatus.BAD_REQUEST.value());
        response.setResponseMessage(MessageConstant.BAD_REQUEST_MESSAGE);
        try {
            var getOneArtist = getArtist(artistId);
            if (getOneArtist.getResponseCode() != HttpStatus.OK.value()) {
                return ArtistResponse.builder()
                        .responseCode(HttpStatus.NOT_FOUND.value())
                        .responseMessage(MessageConstant.NOT_FOUND_MESSAGE)
                        .build();
            }
            artistRepository.deleteById(getOneArtist.getArtist().getArtistId());
            return ArtistResponse.builder()
                    .responseCode(HttpStatus.NOT_FOUND.value())
                    .responseMessage(MessageConstant.NOT_FOUND_MESSAGE)
                    .build();
        } catch (Exception e) {
            response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setResponseMessage(MessageConstant.ERROR_MESSAGE);
        }
        return response;
    }
}
