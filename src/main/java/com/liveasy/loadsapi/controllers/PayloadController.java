package com.liveasy.loadsapi.controllers;

import com.liveasy.loadsapi.entity.PayloadEntity;
import com.liveasy.loadsapi.service.PayloadService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class PayloadController {

    @Autowired
    PayloadService payloadService;

    @RequestMapping(method = RequestMethod.POST, value = "/load")
    public String addLoadDetails(@RequestBody PayloadEntity payloadEntity){
        return payloadService.addLoadDetails(payloadEntity);
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
    public String putLoadDetails(@PathVariable("loadId") String shipperId, @RequestBody PayloadEntity requestPayload){
        return payloadService.putLoadDetails(shipperId, requestPayload);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/load/{loadId}")
    public String deleteLoadDetails(@PathVariable("loadId") String shipperId){
        return payloadService.deleteLoadDetails(shipperId);
    }
}
