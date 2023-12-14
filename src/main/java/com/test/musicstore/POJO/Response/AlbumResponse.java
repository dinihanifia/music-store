package com.test.musicstore.POJO.Response;
import com.test.musicstore.POJO.Entity.Album;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AlbumResponse {
    private int responseCode;
    private String responseMessage;
    private Album album;
}
