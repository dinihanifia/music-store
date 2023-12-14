package com.test.musicstore.POJO.Response;
import com.test.musicstore.POJO.Entity.Album;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetAllAlbumResponse {
    private int responseCode;
    private String responseMessage;
    private List<Album> albumList;
}
