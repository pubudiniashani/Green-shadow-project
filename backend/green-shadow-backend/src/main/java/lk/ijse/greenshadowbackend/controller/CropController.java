package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.dto.impl.CropDTO;
import lk.ijse.greenshadowbackend.service.CropService;
import lk.ijse.greenshadowbackend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
            @RequestParam(value = "cropImage",required = false)MultipartFile cropImage
            ){

        try {

            var buildDTO = new CropDTO();
            buildDTO.setCommonName(commonName);
            buildDTO.setScientificName(scientificName);
            buildDTO.setCategory(category);
            buildDTO.setSeason(season);

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

}
