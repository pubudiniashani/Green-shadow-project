package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.impl.FieldDTO;

public interface FieldService {

    void saveField(FieldDTO fieldDTO);

    void updateField(String fieldId, FieldDTO fieldDTO);

    void deleteField(String fieldId);
}
