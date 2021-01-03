package com.a2.flightservice.dto;

public class PlaneDto {

    private Long planeId;
    private Integer capacity;

    public PlaneDto() {
    }

    public PlaneDto(Long planeId, Integer capacity) {
        this.planeId = planeId;
        this.capacity = capacity;
    }

    public Long getPlaneId() {
        return planeId;
    }

    public void setPlaneId(Long planeId) {
        this.planeId = planeId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
