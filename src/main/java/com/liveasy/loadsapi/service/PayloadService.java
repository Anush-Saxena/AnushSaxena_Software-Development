package com.liveasy.loadsapi.service;

import com.liveasy.loadsapi.entity.PayloadEntity;
import com.liveasy.loadsapi.repositories.PayloadRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class PayloadService {
    @Autowired
    private PayloadRepo payloadRepo;

    public PayloadEntity addLoadDetails(PayloadEntity requestPayload){
        Optional<PayloadEntity> payloadEntity = payloadRepo.findById(requestPayload.getShipperId());
        if (payloadEntity.isEmpty()) {
            payloadRepo.save(requestPayload);
            return requestPayload;
        }
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST," shipperId already Exists! ");
    }

    public PayloadEntity getLoadDetails(String shipperId){
        Optional<PayloadEntity> responseEntity = payloadRepo.findById(shipperId);
        if (responseEntity.isPresent()){
            return responseEntity.get();
        }
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST," shipperId doesn't Exists! ");
    }
    public PayloadEntity putLoadDetails(String shipperId, PayloadEntity requestPayload){
        Optional<PayloadEntity> payloadEntity = payloadRepo.findById(shipperId);
        if (payloadEntity.isPresent()){
            requestPayload.setShipperId(shipperId);
            payloadRepo.save(requestPayload);
            return requestPayload;
        }
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "shipperId doesn't exists");
    }
    public String deleteLoadDetails( String shipperId){
        try {
            payloadRepo.deleteById(shipperId);
            return " Shipping Details Deleted ";
        }
        catch (Exception e){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "shipperId doesn't exists");
        }
    }
}
