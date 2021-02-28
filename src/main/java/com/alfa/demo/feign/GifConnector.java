package com.alfa.demo.feign;

import com.alfa.demo.feign.GIF.Gif;
import com.alfa.demo.feign.GIF.GifObject;
import com.alfa.demo.feign.clients.RateClient;
import com.alfa.demo.feign.clients.SearchCL;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import javafx.scene.layout.Background;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.el.BeanNameResolver;
import java.io.IOException;
import java.util.Random;

@Component
public class GifConnector {
    @Value("${gif.app_id}")
    private String gifAppId;

    private final SearchCL searchCL;

    public GifConnector(@Value("${gif.url}") String url) {
        this.searchCL = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(RateClient.class))
                .logLevel(Logger.Level.FULL)
                .target(SearchCL.class, url);
    }

    public String getRandomGif(boolean isCourseBiggerThenYesterday) {
        String courseValue = isCourseBiggerThenYesterday ? "rich" : "broke";
        Gif object = searchCL.searchGif(gifAppId, courseValue);

        Random random = new Random();
        GifObject value = object.getData().get(random.nextInt(object.getData().size()));

        return value.getImages().getOriginal().getUrl();
    }

}
