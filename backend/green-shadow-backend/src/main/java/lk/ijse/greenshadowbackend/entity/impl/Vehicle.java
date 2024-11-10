package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    private String vehicleId;
    private String licenseNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    private String remarks;
    
}
