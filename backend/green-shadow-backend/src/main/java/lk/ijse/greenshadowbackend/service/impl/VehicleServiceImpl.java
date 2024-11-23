package lk.ijse.greenshadowbackend.service.impl;

import lk.ijse.greenshadowbackend.dao.StaffDao;
import lk.ijse.greenshadowbackend.dao.VehicleDao;
import lk.ijse.greenshadowbackend.dto.impl.VehicleDTO;
import lk.ijse.greenshadowbackend.entity.impl.Staff;
import lk.ijse.greenshadowbackend.entity.impl.Vehicle;
import lk.ijse.greenshadowbackend.service.VehicleService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private Mapping mapping;
    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {

        try {
            Vehicle vehicle = mapping.toVehicleEntity(vehicleDTO);
            Optional<Staff> staff = staffDao.findById(vehicleDTO.getStaff());

            if (!staff.isEmpty()){
                vehicle.setStaff(staff.get());
            }
            vehicle.setVehicleId(UUID.randomUUID().toString());
            vehicleDao.save(vehicle);
        }catch (Exception e){
            throw new RuntimeException("Error saving crop",e);
        }
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
