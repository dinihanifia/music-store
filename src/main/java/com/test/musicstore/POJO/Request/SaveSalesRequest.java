package com.test.musicstore.POJO.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.musicstore.POJO.Entity.Album;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveSalesRequest {
    private Album album;
    @JsonProperty("quantity")
    private int quantity;
//    @JsonProperty("totalAmount")
//    private Long totalAmount;
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;
    @JsonProperty("createdBy")
    private String createdBy;
}
