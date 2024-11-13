package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.dto.EquipmentStatus;
import lk.ijse.greenshadowbackend.entity.impl.Field;
import lk.ijse.greenshadowbackend.entity.impl.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements EquipmentStatus {
    private String equipmentId;
    private String name;
    private String type;
    private String status;
    private Field field;
    private Staff staff;
}
