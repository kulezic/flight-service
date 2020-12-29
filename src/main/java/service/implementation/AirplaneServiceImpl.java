package service.implementation;

import dto.AirplaneCreateDto;
import dto.AirplaneDto;
import mapper.AirplaneMapper;
import model.Airplane;
import repository.AirplaneRepository;
import service.AirplaneService;

public class AirplaneServiceImpl implements AirplaneService {


    AirplaneRepository airplaneRepository;
    AirplaneMapper airplaneMapper;

    public AirplaneServiceImpl(AirplaneRepository airplaneRepository, AirplaneMapper airplaneMapper) {
        this.airplaneRepository = airplaneRepository;
        this.airplaneMapper = airplaneMapper;
    }


    @Override
    public void deleteById(Integer id) {
        airplaneRepository.deleteById(id);
    }

    @Override
    public AirplaneDto add(AirplaneCreateDto airplaneCreateDto) {
        Airplane airplane = airplaneMapper.airplaneCreateDtoToAirplane(airplaneCreateDto);
        airplaneRepository.save(airplane);
        return airplaneMapper.airplaneToAirplaneDto(airplane);
    }
}
