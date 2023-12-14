package com.test.musicstore.Service;
import com.test.musicstore.POJO.Request.SaveSalesRequest;
import com.test.musicstore.POJO.Response.SalesResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SalesService {
    public SalesResponse saveSales(List<SaveSalesRequest> request);
    SalesResponse getAllSales();
    SalesResponse getSalesByCreatedAt(LocalDateTime createdAt);
    SalesResponse deleteSalesById(UUID salesId);
}
