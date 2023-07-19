package com.liveasy.loadsapi.controllers;

import com.liveasy.loadsapi.entity.PayloadEntity;
import com.liveasy.loadsapi.service.PayloadService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;


@RestController
public class PayloadController {

    @Autowired
    PayloadService payloadService;

    public boolean valid(PayloadEntity validateEntity){
        return !(validateEntity.getShipperId() == null || validateEntity.getDate() == null || validateEntity.getLoadingPoint() == null ||
                validateEntity.getProductType() == null || validateEntity.getTruckType() == null || validateEntity.getUnloadingPoint() == null
                || validateEntity.getNoOfTrucks() == null || validateEntity.getWeight() == null);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/load")
    public PayloadEntity addLoadDetails(@RequestBody PayloadEntity payloadEntity){
        if (valid(payloadEntity)){
            return payloadService.addLoadDetails(payloadEntity);
        }
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, " Some details are left empty");

    }

    @RequestMapping(method = RequestMethod.GET, value = "/load")
    public PayloadEntity getLoadDetailsUsingParam(@RequestParam("shipperId") String shipperId){
        return payloadService.getLoadDetails(shipperId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/load/{loadId}")
    public PayloadEntity getLoadDetailsUsingPathVariable(@PathVariable("loadId") String shipperId){
        return payloadService.getLoadDetails(shipperId);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/load/{loadId}")
    public PayloadEntity putLoadDetails(@PathVariable("loadId") String shipperId, @RequestBody PayloadEntity requestPayload){
        if (valid(requestPayload)){
            return payloadService.putLoadDetails(shipperId, requestPayload);
        }
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, " Some details are left empty");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/load/{loadId}")
    public String deleteLoadDetails(@PathVariable("loadId") String shipperId){
        return payloadService.deleteLoadDetails(shipperId);
    }
}
