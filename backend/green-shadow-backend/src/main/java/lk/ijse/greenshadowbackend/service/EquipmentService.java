package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.impl.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);
    void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO);
    void deleteEquipment(String equipmentId);
    List<EquipmentDTO> getAllEquipments();
    EquipmentDTO findById(String equipmentId);
}
