package com.test.musicstore.POJO.Response;
import com.test.musicstore.POJO.Entity.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetAllArtistsResponse {
    private int responseCode;
    private String responseMessage;
    private List<Artist> artistLists;
}
