package com.test.musicstore.Controller;
import com.test.musicstore.Config.URLConstant;
import com.test.musicstore.POJO.Request.GetArtistRequest;
import com.test.musicstore.POJO.Request.SaveArtistRequest;
import com.test.musicstore.POJO.Response.GetAllArtistsResponse;
import com.test.musicstore.POJO.Response.ArtistResponse;
import com.test.musicstore.Service.ArtistService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(URLConstant.ARTIST_URL)
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping(URLConstant.SAVE_URL)
    public ArtistResponse saveArtist(@RequestBody SaveArtistRequest request){
        return artistService.saveArtist(request);
    }
    @GetMapping(URLConstant.GET_ARTIST_URL)
    public ArtistResponse getArtist(@PathVariable("artistId")UUID artistId){
        return artistService.getArtist(artistId);
    }
    @GetMapping(URLConstant.GET_ALL_URL)
    public GetAllArtistsResponse getAllArtists(){
        return artistService.getAllArtists();
    }
    @PutMapping(URLConstant.UPDATE_ARTIST_URL)
    public ArtistResponse updateArtist(@PathVariable("artistId")UUID artistId, @RequestBody SaveArtistRequest saveArtistRequest){
        return artistService.updateArtist(artistId, saveArtistRequest);
    }
    @DeleteMapping(URLConstant.DELETE_ARTIST_URL)
    public ArtistResponse deleteArtist(@PathVariable("artistId")UUID artistId){
        return artistService.deleteArtist(artistId);
    }
}
