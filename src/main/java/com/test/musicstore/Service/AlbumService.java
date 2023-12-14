package com.test.musicstore.Service;
import com.test.musicstore.POJO.Request.SaveAlbumRequest;
import com.test.musicstore.POJO.Response.AlbumResponse;
import com.test.musicstore.POJO.Response.GetAllAlbumResponse;

import java.util.UUID;

public interface AlbumService {
    AlbumResponse saveAlbum(SaveAlbumRequest request);
    GetAllAlbumResponse getAllAlbum();
    AlbumResponse getAlbum(UUID albumId);
    AlbumResponse updateAlbum(UUID albumId, SaveAlbumRequest request);
    AlbumResponse deleteAlbum(UUID albumId);
}
