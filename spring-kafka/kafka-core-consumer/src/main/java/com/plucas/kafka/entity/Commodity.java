package com.plucas.kafka.entity;

public class Commodity {

    private String name;
    private Double price;
    private String measurement;
    private Long timestamp;

    public Commodity() {
    }

    public Commodity(String name, Double price, String measurement, Long timestamp) {
        this.name = name;
        this.setPrice(price);
        this.measurement = measurement;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = Math.round(price * 100d) / 100d;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", measurement='" + measurement + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
