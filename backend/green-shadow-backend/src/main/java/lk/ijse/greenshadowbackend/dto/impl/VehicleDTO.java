package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.dto.VehicleStatus;
import lk.ijse.greenshadowbackend.entity.impl.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements VehicleStatus {
    private String vehicleId;
    private String licenseNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    private String remarks;
    private Staff staff;
}
