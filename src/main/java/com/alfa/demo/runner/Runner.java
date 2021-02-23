package com.alfa.demo.runner;

import com.alfa.demo.dto.RateDto;
import com.alfa.demo.feign.RateConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    @Autowired
    private RateConnector rateConnector;

    @Override
    public void run(String... args) throws Exception {
        RateDto rateDto = rateConnector.getLatestRates(null);
        RateDto rateDto1 = rateConnector.getOldRates(null);
    }
}
