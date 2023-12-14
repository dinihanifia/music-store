package com.test.musicstore.POJO.Response;
import com.test.musicstore.POJO.Entity.Sales;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SalesResponse {
    private int responseCode;
    private String responseMessage;
    private List<Sales> sales;
}
