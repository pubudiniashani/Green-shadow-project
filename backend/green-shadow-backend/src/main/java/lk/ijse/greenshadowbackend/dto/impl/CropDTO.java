package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.dto.CropStatus;
import lk.ijse.greenshadowbackend.entity.impl.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO implements CropStatus {
    private String cropId;
    private String commonName;
    private String scientificName;
    private String category;
    private String season;
    private String cropImage;
    private String field;
}
