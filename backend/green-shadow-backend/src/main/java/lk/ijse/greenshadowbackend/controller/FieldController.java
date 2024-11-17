package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.dto.impl.FieldDTO;
import lk.ijse.greenshadowbackend.service.FieldService;
import lk.ijse.greenshadowbackend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;

   /* @GetMapping
    public String healthTest(){
        return "field controller working";
    }*/

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestPart("fieldId") String fieldId,
            @RequestPart("name") String name,
            @RequestPart("location") String location,
            @RequestPart("extentSize") double extentSize,
            @RequestPart("image1") MultipartFile image1,
            @RequestPart("image2") MultipartFile image2
    ) {

        try {

            var buildDTO = new FieldDTO();
            buildDTO.setFieldId(fieldId);
            buildDTO.setName(name);
            buildDTO.setLocation(location);
            buildDTO.setExtentSize(extentSize);

            if (!image1.isEmpty()) {

                String base64Image1 = AppUtil.imageToBase64(image1.getBytes());
                buildDTO.setImage1(base64Image1);
            }

            if (!image2.isEmpty()) {

                String base64Image2 = AppUtil.imageToBase64(image2.getBytes());
                buildDTO.setImage2(base64Image2);
            }

            fieldService.saveField(buildDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IOException e) {

            e.printStackTrace();
            throw new RuntimeException("Failed to process images", e);
        }
    }
}


