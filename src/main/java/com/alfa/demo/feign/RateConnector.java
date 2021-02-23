package com.alfa.demo.feign;

import com.alfa.demo.dto.RateDto;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate yestDate = date.plusDays(-1);

        RateDto rateDto = rateClient.getOldRates(simpleDateFormat.format(yestDate), appId, base);
        System.out.println(rateDto.toString());
        System.out.println("Изучаю");
        return rateDto;
    }
}


//Здесь же создать метод, который смотрит вчерашние рейты
//1. Создать метод в RateClient(метод возвращает RateDto)
//2. В метод должны передаваться Дата в формате yyyy-mm-dd, app_id, base
//3. Создать здесь метод который вызывает метод созданный в пункте один и возвращающий rateDto
//4. в классе Runner добавить вызов метода созданного в пункте 3
//5. Отправить код на гитхаб и показать мне бибу
