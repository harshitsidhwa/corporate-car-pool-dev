package com.coviam.b2bcarpool.service.implementation;

import com.coviam.b2bcarpool.dto.CarTypeDTO;
import com.coviam.b2bcarpool.service.CarTypeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarTypeServiceImplementation implements CarTypeService {

    private List<CarTypeDTO> resList = new ArrayList<CarTypeDTO>() {
        {
            add(new CarTypeDTO("HATCHBACK"));
            add(new CarTypeDTO("SUV"));
            add(new CarTypeDTO("SEDAN"));
        }
    };

    @Override
    public List<CarTypeDTO> getVehicleTypes() {
        return resList;
    }
}
