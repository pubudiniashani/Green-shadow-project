package lk.ijse.greenshadowbackend.service.impl;

import lk.ijse.greenshadowbackend.dao.FieldDao;
import lk.ijse.greenshadowbackend.dto.impl.FieldDTO;
import lk.ijse.greenshadowbackend.entity.impl.Field;
import lk.ijse.greenshadowbackend.exception.FieldNotFoundException;
import lk.ijse.greenshadowbackend.service.FieldService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;


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
            Field fieldEntity = mapping.toFieldEntity(fieldDTO);
            fieldDao.save(fieldEntity); // Save to database
        } catch (Exception e) {
            throw new RuntimeException("Failed to save field: " + e.getMessage(), e);
        }
    }


    @Override
    public void updateField(String fieldId, FieldDTO fieldDTO) {
        Optional<Field> tmpUser = fieldDao.findById(fieldId);
        if (tmpUser.isPresent()){
            tmpUser.get().setName(fieldDTO.getName());
            tmpUser.get().setLocation(fieldDTO.getLocation());
            tmpUser.get().setExtentSize(fieldDTO.getExtentSize());
            tmpUser.get().setImage1(fieldDTO.getImage1());
            tmpUser.get().setImage2(fieldDTO.getImage2());
        }
    }

    @Override
    public void deleteField(String fieldId) {
        Optional<Field>  existedField=  fieldDao.findById(fieldId);
        if (!existedField.isPresent()){
            throw new FieldNotFoundException("Field with this ID is not found");
        }else {
            fieldDao.deleteById(fieldId);
        }
    }
}
