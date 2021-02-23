package com.alfa.demo.dto;

public class RateDto {
    private String base;
    private String disclaimer;
    private String license;
    private Long timestamp;
    private RubRate rates;

    @Override
    public String toString() {
        return String.format("Current rub course: %s", rates != null ? rates.getRUB() : null);
    }

    public String getBase() {

        return base;
    }

    public void setBase(String base) {

        this.base = base;
    }

    public String getDisclaimer() {

        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {

        this.disclaimer = disclaimer;
    }

    public String getLicense() {

        return license;
    }

    public void setLicense(String license) {

        this.license = license;
    }

    public Long getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(Long timestamp) {

        this.timestamp = timestamp;
    }

    public RubRate getRates() {

        return rates;
    }

    public void setRates(RubRate rates)
    {
        this.rates = rates;
    }

    public static class RubRate {
        private Double RUB;

        public Double getRUB() {

            return RUB;
        }

        public void setRUB(Double RUB) {
            this.RUB = RUB;
        }
    }
}
