package lk.ijse.greenshadowbackend.service.impl;

import lk.ijse.greenshadowbackend.dto.impl.FieldDTO;
import lk.ijse.greenshadowbackend.service.FieldService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {

    @Override
    public void saveField(FieldDTO fieldDTO) {

    }

    @Override
    public void updateField(String fieldId, FieldDTO fieldDTO) {

    }

    @Override
    public void deleteField(String fieldId) {

    }
}
