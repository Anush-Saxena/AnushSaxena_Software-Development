package com.liveasy.loadsapi.service;

import com.liveasy.loadsapi.entity.PayloadEntity;
import com.liveasy.loadsapi.repositories.PayloadRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class PayloadService {
    @Autowired
    private PayloadRepo payloadRepo;

    public boolean valid(PayloadEntity validateEntity){
        return !(validateEntity.getShipperId().equals("") || validateEntity.getDate().equals("") || validateEntity.getLoadingPoint().equals("") ||
                validateEntity.getProductType().equals("") || validateEntity.getTruckType().equals("") || validateEntity.getUnloadingPoint().equals("")
                || validateEntity.getNoOfTrucks().equals("") || validateEntity.getWeight().equals(""));
    }

    public String addLoadDetails(PayloadEntity requestPayload){
        if (valid(requestPayload)){
            Optional<PayloadEntity> payloadEntity = payloadRepo.findById(requestPayload.getShipperId());
            if (payloadEntity.isEmpty()) {
                payloadRepo.save(requestPayload);
                return "Payload Details added successfully";
            }
            return "shipperId already exists! ";
        }
        return "Some fields are left empty! Please Check";
    }

    public PayloadEntity getLoadDetails(String shipperId){
        Optional<PayloadEntity> responseEntity = payloadRepo.findById(shipperId);
        if (responseEntity.isPresent()){
            return responseEntity.get();
        }
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST," shipperId doesn't Exists! ");
    }
    public String putLoadDetails(String shipperId, PayloadEntity requestPayload){
        if (valid(requestPayload)){
            Optional<PayloadEntity> payloadEntity = payloadRepo.findById(shipperId);
            if (payloadEntity.isPresent()) {
                payloadRepo.save(requestPayload);
                return "Details Updated Successfully";
            }
            return "shipperId doesn't exists";
        }
        return "Some fields are left empty! Please Check";
    }
    public String deleteLoadDetails( String shipperId){
        if (payloadRepo.existsById(shipperId)){
            payloadRepo.deleteById(shipperId);
            return " Shipping Details Deleted ";
        }
        else{
            return "shipperId doesn't exists!";
        }
    }
}
