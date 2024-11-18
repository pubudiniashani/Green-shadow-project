package lk.ijse.greenshadowbackend.controller;
import lk.ijse.greenshadowbackend.dto.impl.FieldDTO;
import lk.ijse.greenshadowbackend.exception.FieldNotFoundException;
import lk.ijse.greenshadowbackend.service.FieldService;
import lk.ijse.greenshadowbackend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestParam("name") String name,
            @RequestParam("location") String location,
            @RequestParam("extentSize") double extentSize,
            @RequestParam(value = "image1",required = false) MultipartFile image1,
            @RequestParam(value = "image2",required = false) MultipartFile image2
            ){

        try {

            var buildDTO = new FieldDTO();
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
        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("Failed to process images", e);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{fieldId}",consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateField(
            @RequestParam("name") String name,
            @RequestParam("location") String location,
            @RequestParam("extentSize") double extentSize,
            @RequestParam("image1") MultipartFile image1,
            @RequestParam("image2") MultipartFile image2,

            @PathVariable("fieldId") String fieldId
    ){
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

            fieldService.updateField(fieldId,buildDTO);
        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("Failed to process images", e);
        }
    }
    @DeleteMapping(value = "/{fieldId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("fieldId") String fieldId) {

        try {
            fieldService.deleteField(fieldId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FieldNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getAllFields(){
        return fieldService.getAllFields();
    }
}



