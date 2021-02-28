package com.alfa.demo.feign;

import com.alfa.demo.dto.RateDto;
import com.alfa.demo.feign.clients.RateClient;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;



@Component
public class RateConnector {
    @Value("${rates.app_id}")
    private String appId;

    private final RateClient rateClient;


    public RateConnector(@Value("${rates.url}") String url) {
        this.rateClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(RateClient.class))
                .logLevel(Logger.Level.FULL)
                .target(RateClient.class, url);
    }

    public RateDto getLatestRates(String base) {
        if (base == null) {
            base = "USD";
        }
        RateDto rateDto = rateClient.findLatest(appId, base);
        System.out.println(rateDto.toString());
        return rateDto;

    }

    public RateDto getOldRates(String base) {
        if (base == null) {
            base = "USD";
        }
        LocalDate date= LocalDate.now();
        LocalDate yestDate = date.plusDays(-1);

        RateDto rateDto = rateClient.getOldRates(yestDate.toString(), appId, base);
        System.out.println(rateDto.toString());
        return rateDto;
    }

    public boolean isLatestRatesUpperThanYesterday(String base) {
        RateDto latestRates = getLatestRates(base);
        RateDto yesterdayRates = getOldRates(base);

        return latestRates.getRates().getRUB() > yesterdayRates.getRates().getRUB();
    }
}

