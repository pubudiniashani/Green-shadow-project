package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "staffId")
    private Staff staff;
}
