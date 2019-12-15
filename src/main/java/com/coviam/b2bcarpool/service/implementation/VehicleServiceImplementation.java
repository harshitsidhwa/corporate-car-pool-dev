package com.coviam.b2bcarpool.service.implementation;

import com.coviam.b2bcarpool.dto.VehicleRegisterRequestDTO;
import com.coviam.b2bcarpool.dto.VehicleResponseDTO;
import com.coviam.b2bcarpool.dto.VehicleTypeDTO;
import com.coviam.b2bcarpool.models.Vehicles;
import com.coviam.b2bcarpool.repository.VehicleRepository;
import com.coviam.b2bcarpool.service.VehicleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImplementation implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    private List<VehicleTypeDTO> resList = new ArrayList<VehicleTypeDTO>() {
        {
            add(new VehicleTypeDTO("HATCHBACK"));
            add(new VehicleTypeDTO("SUV"));
            add(new VehicleTypeDTO("SEDAN"));
        }
    };

    @Override
    public List<VehicleTypeDTO> getVehicleTypes() {
        return resList;
    }

    @Override
    public List<VehicleResponseDTO> getUserVehicles(String userId) {
        List<VehicleResponseDTO> responseDTO = new ArrayList<>();
        List<Vehicles> userVehicles = vehicleRepository.findByUserId(userId);
        for (Vehicles vehicle : userVehicles) {
            VehicleResponseDTO vehicleResponseDTO = new VehicleResponseDTO();
            vehicleResponseDTO.setValue(vehicle.getVehicleNumber());
            vehicleResponseDTO.setText(vehicle.getVehicleName() + " | " + vehicle.getVehicleColor());
            responseDTO.add(vehicleResponseDTO);
        }
        return responseDTO;
    }

    @Override
    public boolean addNewVehicle(String userId, VehicleRegisterRequestDTO registerRequestDTO) {
        if (vehicleRepository.existsByVehicleNumber(registerRequestDTO.getVehicleNumber().toUpperCase())) {
            return false;
        }
        Vehicles newVehicle = new Vehicles();
        BeanUtils.copyProperties(registerRequestDTO, newVehicle);
        newVehicle.setUserId(userId);
        vehicleRepository.save(newVehicle);
        return true;
    }
}
