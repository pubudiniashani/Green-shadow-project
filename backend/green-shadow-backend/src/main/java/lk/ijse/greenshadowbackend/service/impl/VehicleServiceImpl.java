package lk.ijse.greenshadowbackend.service.impl;

import lk.ijse.greenshadowbackend.dto.impl.VehicleDTO;
import lk.ijse.greenshadowbackend.service.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {

    }

    @Override
    public void updateVehicle(String vehicleId, VehicleDTO vehicleDTO) {

    }

    @Override
    public void deleteVehicle(String vehicleId) {

    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return null;
    }
}
