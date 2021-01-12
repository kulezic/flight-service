package com.a2.flightservice.dto;

public class PlaneCreateDto {

    private String name;
    private Integer capacity;

    public PlaneCreateDto() {
    }

    public PlaneCreateDto(Integer capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
