package com.test.musicstore.POJO.Request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaveArtistRequest {
    private String artistName;
    private String artistCountry;
}
