package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.impl.CropDTO;

import java.util.List;

public interface CropService {

    void saveCrop(CropDTO cropDTO);
    void updateCrop(String cropId, CropDTO cropDTO);
    void deleteCrop(String cropId);
    List<CropDTO> getAllCrops();
}
