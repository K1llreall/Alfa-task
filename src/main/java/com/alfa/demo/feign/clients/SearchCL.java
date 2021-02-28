package com.alfa.demo.feign.clients;
import com.alfa.demo.feign.GIF.Gif;
import feign.Param;
import feign.RequestLine;

public interface SearchCL {
    @RequestLine("GET /v1/gifs/search?api_key={api_key}&q={q}")
    Gif searchGif(@Param(value = "api_key") String gifApp_id, @Param(value = "q") String q );
}


