package com.test.musicstore.Controller;
import com.test.musicstore.Config.URLConstant;
import com.test.musicstore.POJO.Request.SaveSalesRequest;
import com.test.musicstore.POJO.Response.SalesResponse;
import com.test.musicstore.Service.SalesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(URLConstant.SALES_URL)
public class SalesController {
    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }
    @PostMapping(URLConstant.SAVE_URL)
    public SalesResponse saveSales(@RequestBody List<SaveSalesRequest> request){
        return salesService.saveSales(request);
    }
    @GetMapping(URLConstant.GET_ALL_URL)
    public SalesResponse getAllSales(){
        return salesService.getAllSales();
    }
    @GetMapping(URLConstant.GET_SALES_URL)
    public SalesResponse getSalesByCreatedAt(@RequestParam(name = "createdAt")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdAt){
        LocalDateTime newCreatedAt = createdAt.atStartOfDay();
        return salesService.getSalesByCreatedAt(newCreatedAt);
    }
    @DeleteMapping(URLConstant.DELETE_SALES_URL)
    public SalesResponse deleteSalesById(@PathVariable(name = "salesId") UUID salesId){
        return salesService.deleteSalesById(salesId);
    }
}
