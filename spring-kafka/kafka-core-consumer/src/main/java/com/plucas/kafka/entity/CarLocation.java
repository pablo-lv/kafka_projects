package com.plucas.kafka.entity;

public class CarLocation {

    private String carId;
    private Long timestamp;
    private Integer distance;

    public CarLocation() {
    }

    public CarLocation(String carId, Long timestamp, Integer distance) {
        this.carId = carId;
        this.timestamp = timestamp;
        this.distance = distance;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "CarLocation{" +
                "carId='" + carId + '\'' +
                ", timestamp=" + timestamp +
                ", distance=" + distance +
                '}';
    }
}
