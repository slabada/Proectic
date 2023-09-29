package com.gateway.gatewaymicroservice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String x(){
        return "XYI";
    }

    @GetMapping("/test2")
    public String x2(){
        return "XYI2";
    }
}
