package service;

import dto.AirplaneCreateDto;
import dto.AirplaneDto;

public interface AirplaneService {

    void deleteById(Integer id);

    AirplaneDto add(AirplaneCreateDto airplaneCreateDto);
}
