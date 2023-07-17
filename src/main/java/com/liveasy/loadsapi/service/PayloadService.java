package com.liveasy.loadsapi.service;

import com.liveasy.loadsapi.entity.PayloadEntity;
import com.liveasy.loadsapi.repositories.PayloadRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class PayloadService {
    @Autowired
    private PayloadRepo payloadRepo;

    public String addLoadDetails(PayloadEntity payloadEntity){
        if (!payloadRepo.existsById(payloadEntity.getShipperId())){
            payloadRepo.save(payloadEntity);
            return "Loads details added Successfully";
        }
        else{
            return "Loads detail exist with this Shipper ID";
        }
    }
    public PayloadEntity getLoadDetails(String shipperId){
        if (payloadRepo.existsById(shipperId)){
            return payloadRepo.findById(shipperId).get();
        }
        else{
            return null;
        }
    }
    public String putLoadDetails(String shipperId, PayloadEntity requestPayload){
        if(payloadRepo.existsById(shipperId)){
            requestPayload.setShipperId(shipperId);
            payloadRepo.save(requestPayload);
            return "Details Updated";
        }
        else{
            return "Shipper Id does not exists!";
        }
    }
    public String deleteLoadDetails( String shipperId){
        if (payloadRepo.existsById(shipperId)){
            payloadRepo.deleteById(shipperId);
            return "Pay Load Details deleted successfully";
        }
        else{
            return "Shipper Id doesn't exists";
        }
    }

}
