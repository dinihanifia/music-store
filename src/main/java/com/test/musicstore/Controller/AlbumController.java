package com.test.musicstore.Controller;
import com.test.musicstore.Config.URLConstant;
import com.test.musicstore.POJO.Request.SaveAlbumRequest;
import com.test.musicstore.POJO.Response.AlbumResponse;
import com.test.musicstore.POJO.Response.GetAllAlbumResponse;
import com.test.musicstore.Service.AlbumService;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping(URLConstant.ALBUM_URL)
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }
    @PostMapping(URLConstant.SAVE_URL)
    public AlbumResponse saveAlbum(@RequestBody SaveAlbumRequest request){
        return albumService.saveAlbum(request);
    }
    @GetMapping(URLConstant.GET_ALL_URL)
    public GetAllAlbumResponse getAllAlbum(){
        return albumService.getAllAlbum();
    }
    @GetMapping(URLConstant.GET_ALBUM_URL)
    public AlbumResponse getAlbum(@PathVariable("albumId") UUID albumId){
        return albumService.getAlbum(albumId);
    }
    @PutMapping(URLConstant.UPDATE_ALBUM_URL)
    public AlbumResponse updateAlbum(@PathVariable("albumId") UUID albumId, @RequestBody SaveAlbumRequest request){
        return albumService.updateAlbum(albumId, request);
    }
    @DeleteMapping(URLConstant.DELETE_ALBUM_URL)
    public AlbumResponse deleteAlbum(@PathVariable("albumId") UUID albumId){
        return albumService.deleteAlbum(albumId);
    }
}
