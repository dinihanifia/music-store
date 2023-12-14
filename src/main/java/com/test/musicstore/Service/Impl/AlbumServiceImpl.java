package com.test.musicstore.Service.Impl;
import com.test.musicstore.Config.MessageConstant;
import com.test.musicstore.POJO.Entity.Album;
import com.test.musicstore.POJO.Entity.Artist;
import com.test.musicstore.POJO.Request.SaveAlbumRequest;
import com.test.musicstore.POJO.Response.AlbumResponse;
import com.test.musicstore.POJO.Response.GetAllAlbumResponse;
import com.test.musicstore.Repository.AlbumRepository;
import com.test.musicstore.Repository.ArtistRepository;
import com.test.musicstore.Service.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public AlbumResponse saveAlbum(SaveAlbumRequest request) {
        AlbumResponse response = new AlbumResponse();
        response.setResponseCode(HttpStatus.BAD_REQUEST.value());
        response.setResponseMessage(MessageConstant.BAD_REQUEST_MESSAGE);
        try{
            Artist artist = new Artist();
            Optional<Artist> existingArtist = Optional.empty();
            if(request.getArtist().getArtistId()!=null){
                existingArtist = artistRepository.findById(request.getArtist().getArtistId());
            }
            if (existingArtist.isPresent()) {
                artist = existingArtist.get();
            }
            artist.setArtistName(request.getArtist().getArtistName());
            artist.setArtistCountry(request.getArtist().getArtistCountry());
            artistRepository.save(artist);

            Album album = new Album();
            album.setAlbumName(request.getAlbumName());
            album.setArtist(artist);
            album.setAlbumPrice(request.getAlbumPrice());
            var saveAlbum = albumRepository.save(album);
            return AlbumResponse.builder()
                    .responseCode(HttpStatus.OK.value())
                    .responseMessage(MessageConstant.SUCCESS_MESSAGE)
                    .album(saveAlbum)
                    .build();
        } catch (Exception e){
            response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setResponseMessage(MessageConstant.ERROR_MESSAGE);
        }
        return response;
    }

    @Override
    public GetAllAlbumResponse getAllAlbum() {
        GetAllAlbumResponse response = new GetAllAlbumResponse();
        response.setResponseCode(HttpStatus.BAD_REQUEST.value());
        response.setResponseMessage(MessageConstant.BAD_REQUEST_MESSAGE);
        try{
            var existAlbum = albumRepository.findAll();
            List<Album> albumList = existAlbum.stream().collect(Collectors.toList());
            return GetAllAlbumResponse.builder()
                    .responseCode(HttpStatus.OK.value())
                    .responseMessage(MessageConstant.SUCCESS_MESSAGE)
                    .albumList(albumList)
                    .build();
        } catch (Exception e){
            response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setResponseMessage(MessageConstant.ERROR_MESSAGE);
        }
        return response;
    }

    @Override
    public AlbumResponse getAlbum(UUID albumId) {
        AlbumResponse response = new AlbumResponse();
        response.setResponseCode(HttpStatus.BAD_REQUEST.value());
        response.setResponseMessage(MessageConstant.BAD_REQUEST_MESSAGE);
        try{
            Optional<Album> existAlbum = albumRepository.findById(albumId);
            existAlbum.ifPresentOrElse(
                    album -> {
                        response.setResponseCode(HttpStatus.OK.value());
                        response.setResponseMessage(MessageConstant.SUCCESS_MESSAGE);
                        response.setAlbum(album);
                    },
                    () -> {
                        response.setResponseCode(HttpStatus.NOT_FOUND.value());
                        response.setResponseMessage(MessageConstant.NOT_FOUND_MESSAGE);
                    }
            );
        } catch (Exception e){
            response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setResponseMessage(MessageConstant.ERROR_MESSAGE);
        }
        return response;
    }

    @Override
    public AlbumResponse updateAlbum(UUID albumId, SaveAlbumRequest request) {
        AlbumResponse response = new AlbumResponse();
        response.setResponseCode(HttpStatus.BAD_REQUEST.value());
        response.setResponseMessage(MessageConstant.BAD_REQUEST_MESSAGE);
        try{
            var checkAlbumId = getAlbum(albumId);
            if(checkAlbumId.getResponseCode()==HttpStatus.OK.value()){
                var updateAlbum = checkAlbumId.getAlbum();
                updateAlbum.setAlbumName(request.getAlbumName());
                updateAlbum.setArtist(request.getArtist());
                updateAlbum.setAlbumPrice(request.getAlbumPrice());
                albumRepository.save(updateAlbum);
                return AlbumResponse.builder()
                        .responseCode(HttpStatus.OK.value())
                        .responseMessage(MessageConstant.SUCCESS_MESSAGE)
                        .album(updateAlbum)
                        .build();
            }
            return checkAlbumId;
        } catch (Exception e){
            response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setResponseMessage(MessageConstant.ERROR_MESSAGE);
        }
        return response;
    }

    @Override
    public AlbumResponse deleteAlbum(UUID albumId) {
        AlbumResponse response = new AlbumResponse();
        response.setResponseCode(HttpStatus.BAD_REQUEST.value());
        response.setResponseMessage(MessageConstant.BAD_REQUEST_MESSAGE);
        try{
            var checkAlbumId = getAlbum(albumId);
            if (checkAlbumId.getResponseCode() != HttpStatus.OK.value()) {
                return AlbumResponse.builder()
                        .responseCode(HttpStatus.NOT_FOUND.value())
                        .responseMessage(MessageConstant.NOT_FOUND_MESSAGE)
                        .build();
            }
            albumRepository.deleteById(checkAlbumId.getAlbum().getAlbumId());
            return AlbumResponse.builder()
                    .responseCode(HttpStatus.OK.value())
                    .responseMessage(MessageConstant.SUCCESS_MESSAGE)
                    .build();
        } catch (Exception e){
            response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setResponseMessage(MessageConstant.ERROR_MESSAGE);
        }
        return response;
    }
}
