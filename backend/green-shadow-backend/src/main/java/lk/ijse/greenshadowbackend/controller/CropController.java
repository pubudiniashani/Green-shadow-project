package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.dto.impl.CropDTO;
import lk.ijse.greenshadowbackend.entity.impl.Crop;
import lk.ijse.greenshadowbackend.exception.FieldNotFoundException;
import lk.ijse.greenshadowbackend.service.CropService;
import lk.ijse.greenshadowbackend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/crops")
public class CropController {

    @Autowired
    private CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveCrop(
            @RequestParam("commonName") String commonName,
            @RequestParam("scientificName") String scientificName,
            @RequestParam("category") String category,
            @RequestParam("season") String season,
            @RequestParam(value = "cropImage",required = false)MultipartFile cropImage,
            @RequestParam("field") String field
            ){

        try {

            var buildDTO = new CropDTO();
            buildDTO.setCommonName(commonName);
            buildDTO.setScientificName(scientificName);
            buildDTO.setCategory(category);
            buildDTO.setSeason(season);
            buildDTO.setField(field);
            System.out.println(field );

            if (!cropImage.isEmpty()) {

                String base64Image1 = AppUtil.imageToBase64(cropImage.getBytes());
                buildDTO.setCropImage(base64Image1);
            }

            cropService.saveCrop(buildDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){

            e.printStackTrace();
            throw new RuntimeException("Failed to process images", e);
        }

    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{cropId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateCrop(
            @RequestParam("commonName") String commonName,
            @RequestParam("scientificName") String scientificName,
            @RequestParam("category") String category,
            @RequestParam("season") String season,
            @RequestParam(value = "cropImage",required = false)MultipartFile cropImage,
            @RequestParam("field") String field,

            @PathVariable("cropId") String cropId
        ) {
        try {

            var buildDTO = new CropDTO();
            buildDTO.setCommonName(commonName);
            buildDTO.setScientificName(scientificName);
            buildDTO.setCategory(category);
            buildDTO.setSeason(season);
            buildDTO.setField(field);


            if (!cropImage.isEmpty()) {

                String base64Image1 = AppUtil.imageToBase64(cropImage.getBytes());
                buildDTO.setCropImage(base64Image1);
            }

            cropService.updateCrop(cropId,buildDTO);
        }catch (Exception e){

            e.printStackTrace();
            throw new RuntimeException("Failed to process images", e);
        }

    }

    @DeleteMapping(value = "/{cropId}")
    public ResponseEntity<Void> deleteCrop(@PathVariable("cropId") String cropId) {

        try {
            cropService.deleteCrop(cropId);
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
    public List<CropDTO> getAllCrops(){
        return cropService.getAllCrops();
    }

    @GetMapping(value = "/{cropId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CropDTO> getCropById(@PathVariable ("cropId") String cropId){
        CropDTO crop = cropService.findById(cropId);
        if (crop == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(crop,HttpStatus.OK);
    }

}
