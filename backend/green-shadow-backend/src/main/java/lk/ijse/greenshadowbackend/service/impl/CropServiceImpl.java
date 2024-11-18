package lk.ijse.greenshadowbackend.service.impl;

import lk.ijse.greenshadowbackend.dao.CropDao;
import lk.ijse.greenshadowbackend.dto.impl.CropDTO;
import lk.ijse.greenshadowbackend.entity.impl.Crop;
import lk.ijse.greenshadowbackend.service.CropService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CropServiceImpl implements CropService {

    @Autowired
    private CropDao cropDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveCrop(CropDTO cropDTO) {
        try {
            Crop crop = mapping.toCropEntity(cropDTO);
            crop.setCropId(UUID.randomUUID().toString());
            cropDao.save(crop);
        }catch (Exception e){
            throw new RuntimeException("Error saving crop",e);
        }
    }

    @Override
    public void updateCrop(String cropId, CropDTO cropDTO) {

    }

    @Override
    public void deleteCrop(String cropId) {

    }

    @Override
    public List<CropDTO> getAllCrops() {
        return null;
    }
}
