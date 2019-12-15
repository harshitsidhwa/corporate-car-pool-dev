package com.coviam.b2bcarpool.service;

import com.coviam.b2bcarpool.dto.VehicleRegisterRequestDTO;
import com.coviam.b2bcarpool.dto.VehicleResponseDTO;
import com.coviam.b2bcarpool.dto.VehicleTypeDTO;

import java.util.List;

public interface VehicleService {
    List<VehicleTypeDTO> getVehicleTypes();

    List<VehicleResponseDTO> getUserVehicles(String userId);

    boolean addNewVehicle(String userId, VehicleRegisterRequestDTO registerRequestDTO);
}
