package lk.ijse.greenshadowbackend.service.impl;

import lk.ijse.greenshadowbackend.dao.FieldDao;
import lk.ijse.greenshadowbackend.dto.impl.FieldDTO;
import lk.ijse.greenshadowbackend.entity.impl.Field;
import lk.ijse.greenshadowbackend.service.FieldService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private Mapping mapping;
   /* @Override
    public void saveField(FieldDTO fieldDTO) {
        Field savedField = fieldDao.save(mapping.toFieldEntity(fieldDTO));
        if (savedField == null){
            throw new RuntimeException("Field not found");
        }
    }*/

    @Override
    public void saveField(FieldDTO fieldDTO) {
        try {
            Field fieldEntity = mapping.toFieldEntity(fieldDTO); // Convert DTO to entity
            fieldDao.save(fieldEntity); // Save to database
        } catch (Exception e) {
            // Handle and throw a meaningful exception if something goes wrong
            throw new RuntimeException("Failed to save field: " + e.getMessage(), e);
        }
    }


    @Override
    public void updateField(String fieldId, FieldDTO fieldDTO) {

    }

    @Override
    public void deleteField(String fieldId) {

    }
}
