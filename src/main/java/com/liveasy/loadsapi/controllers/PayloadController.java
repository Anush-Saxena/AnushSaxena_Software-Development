package com.liveasy.loadsapi.controllers;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class PayloadController {

    @RequestMapping(method = RequestMethod.POST, value = "/load")
    public String addLogisticDetails(){
        return null;
    }

}
