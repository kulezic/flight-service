package mapper;

import dto.AirplaneCreateDto;
import dto.AirplaneDto;
import model.Airplane;
import org.springframework.stereotype.Component;

@Component
public class AirplaneMapper {


    public AirplaneDto airplaneToAirplaneDto(Airplane airplane){
        AirplaneDto airplaneDto = new AirplaneDto();
        airplaneDto.setIdAirplane(airplane.getIdAirplane());
        airplaneDto.setName(airplane.getName());
        airplaneDto.setCapacity(airplane.getCapacity());
        return airplaneDto;
    }

    public Airplane airplaneCreateDtoToAirplane(AirplaneCreateDto airplaneCreateDto){
        Airplane airplane = new Airplane();
        airplane.setName(airplaneCreateDto.getName());
        airplane.setCapacity(airplaneCreateDto.getCapacity());
        return airplane;
    }
}
