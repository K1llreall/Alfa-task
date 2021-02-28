package com.alfa.demo.controller;

import com.alfa.demo.feign.GifConnector;
import com.alfa.demo.feign.RateConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @Autowired
    private RateConnector rateConnector;

    @Autowired
    private GifConnector gifConnector;

    @Value("${default.base}")
    private String defaultBase;

    @GetMapping
    public ResponseEntity<String> demo(@RequestParam(name = "base", required = false) String base) {
        if(base == null || base.isBlank()) {
            base = defaultBase;
        } else if(!base.equalsIgnoreCase("USD")) {
            return new ResponseEntity<>("Программа поддерживает только USD", HttpStatus.BAD_REQUEST);
        }

        boolean isLatestRatesUpperThanYesterday = rateConnector.isLatestRatesUpperThanYesterday(base);
        return new ResponseEntity<>(gifConnector.getRandomGif(isLatestRatesUpperThanYesterday), HttpStatus.OK);
    }

}
