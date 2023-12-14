package com.test.musicstore.Service.Impl;
import com.test.musicstore.Config.MessageConstant;
import com.test.musicstore.POJO.Entity.Album;
import com.test.musicstore.POJO.Entity.Sales;
import com.test.musicstore.POJO.Request.SaveSalesRequest;
import com.test.musicstore.POJO.Response.AlbumResponse;
import com.test.musicstore.POJO.Response.SalesResponse;
import com.test.musicstore.Repository.AlbumRepository;
import com.test.musicstore.Repository.SalesRepository;
import com.test.musicstore.Service.SalesService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;
    private final AlbumRepository albumRepository;

    public SalesServiceImpl(SalesRepository salesRepository, AlbumRepository albumRepository) {
        this.salesRepository = salesRepository;
        this.albumRepository = albumRepository;
    }

    public SalesResponse saveSales(List<SaveSalesRequest> request) {
        SalesResponse response = new SalesResponse();
        response.setResponseCode(HttpStatus.BAD_REQUEST.value());
        response.setResponseMessage(MessageConstant.BAD_REQUEST_MESSAGE);
        try {
            List<Sales> salesList = request.stream()
                    .map(saveSalesRequest -> {
                        Sales sales = new Sales();
                        Optional<Album> getAlbum = albumRepository.findById(saveSalesRequest.getAlbum().getAlbumId());
                        getAlbum.ifPresentOrElse(
                                album -> {
                                    sales.setAlbum(album);
                                    sales.setQuantity(saveSalesRequest.getQuantity());
                                    sales.setTotalAmount(Long.valueOf(saveSalesRequest.getQuantity() * album.getAlbumPrice()));
                                    sales.setCreatedBy(saveSalesRequest.getCreatedBy());
                                },
                                () -> {
                                    response.setResponseCode(HttpStatus.NOT_FOUND.value());
                                    response.setResponseMessage(MessageConstant.NOT_FOUND_MESSAGE);
                                });

                        return sales;
                    })
                    .filter(sales -> sales.getAlbum() != null)
                    .collect(Collectors.toList());
            List<Sales> savedSalesList = salesRepository.saveAll(salesList);
            return SalesResponse.builder()
                    .responseCode(HttpStatus.OK.value())
                    .responseMessage(MessageConstant.SUCCESS_MESSAGE)
                    .sales(savedSalesList)
                    .build();
        } catch (Exception e) {
            response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setResponseMessage(MessageConstant.ERROR_MESSAGE);
            return response;
        }
    }

    @Override
    public SalesResponse getAllSales() {
        SalesResponse response = new SalesResponse();
        response.setResponseCode(HttpStatus.BAD_REQUEST.value());
        response.setResponseMessage(MessageConstant.BAD_REQUEST_MESSAGE);
        try{
            var existSales = salesRepository.findAll();
            List<Sales> salesList= existSales.stream().collect(Collectors.toList());
            return SalesResponse.builder()
                    .responseCode(HttpStatus.OK.value())
                    .responseMessage(MessageConstant.SUCCESS_MESSAGE)
                    .sales(salesList)
                    .build();
        } catch (Exception e){
            response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setResponseMessage(MessageConstant.ERROR_MESSAGE);
            return response;
        }
    }

    @Override
    public SalesResponse getSalesByCreatedAt(LocalDateTime createdAt) {
        SalesResponse response = new SalesResponse();
        response.setResponseCode(HttpStatus.BAD_REQUEST.value());
        response.setResponseMessage(MessageConstant.BAD_REQUEST_MESSAGE);
        try{
            LocalDateTime startOfDay = createdAt.toLocalDate().atStartOfDay();
            LocalDateTime endOfDay = createdAt.toLocalDate().atTime(LocalTime.MAX);
            var filter = salesRepository.findByCreatedAtBetween(startOfDay, endOfDay);
            List<Sales> salesList = filter.stream().collect(Collectors.toList());
            return SalesResponse.builder()
                    .responseCode(HttpStatus.OK.value())
                    .responseMessage(MessageConstant.SUCCESS_MESSAGE)
                    .sales(salesList)
                    .build();
        } catch (Exception e){
            response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setResponseMessage(MessageConstant.ERROR_MESSAGE);
            return response;
        }
    }

    @Override
    public SalesResponse deleteSalesById(UUID salesId) {
        SalesResponse response = new SalesResponse();
        response.setResponseCode(HttpStatus.BAD_REQUEST.value());
        response.setResponseMessage(MessageConstant.BAD_REQUEST_MESSAGE);
        try{
            var checkSalesById = salesRepository.findById(salesId);
            checkSalesById.ifPresentOrElse(
                    sales -> {
                        salesRepository.deleteById(checkSalesById.get().getSalesId());
                        response.setResponseCode(HttpStatus.OK.value());
                        response.setResponseMessage(MessageConstant.SUCCESS_MESSAGE);
                    },
                    () -> {
                        response.setResponseCode(HttpStatus.NOT_FOUND.value());
                        response.setResponseMessage(MessageConstant.NOT_FOUND_MESSAGE);
                    }
            );
            return response;
        } catch (Exception e){
            response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setResponseMessage(MessageConstant.ERROR_MESSAGE);
            return response;
        }
    }
}
