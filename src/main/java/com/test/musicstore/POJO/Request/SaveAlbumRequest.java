package com.test.musicstore.POJO.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.musicstore.POJO.Entity.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaveAlbumRequest {
    private UUID albumId;
    @JsonProperty("albumName")
    private String albumName;
    @JsonProperty("artist")
    private Artist artist;
    @JsonProperty("albumPrice")
    private int albumPrice;
}
