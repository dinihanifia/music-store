package com.test.musicstore.POJO.Response;
import com.test.musicstore.POJO.Entity.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ArtistResponse {
    private int responseCode;
    private String responseMessage;
    private Artist artist;
}
