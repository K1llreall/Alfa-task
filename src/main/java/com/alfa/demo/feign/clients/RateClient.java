package com.alfa.demo.feign.clients;

import com.alfa.demo.dto.RateDto;
import feign.Param;
import feign.RequestLine;


public interface RateClient {
    @RequestLine("GET /latest.json?app_id={app_id}&base={base}")
    RateDto findLatest(@Param("app_id") String appId, @Param("base") String base);

    @RequestLine("GET /historical/{date}.json?app_id={app_id}&base={base}")
    RateDto getOldRates(@Param("date") String yesterday,@Param("app_id") String appId,@Param("base") String base);

}





