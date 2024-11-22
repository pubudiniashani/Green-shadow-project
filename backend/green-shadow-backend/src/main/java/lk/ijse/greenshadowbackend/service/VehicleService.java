package lk.ijse.greenshadowbackend.service;


import lk.ijse.greenshadowbackend.dto.impl.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);
    void updateVehicle(String vehicleId, VehicleDTO vehicleDTO);
    void deleteVehicle(String vehicleId);
    List<VehicleDTO> getAllVehicles();
}
