package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.dto.impl.CropDTO;
import lk.ijse.greenshadowbackend.dto.impl.LogDTO;
import lk.ijse.greenshadowbackend.dto.impl.VehicleDTO;
import lk.ijse.greenshadowbackend.service.LogService;
import lk.ijse.greenshadowbackend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@RestController
@RequestMapping("api/v1/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveLog(
            @RequestParam("date") Date date,
            @RequestParam("logDetails") String logDetails,
            @RequestParam(value = "observedImage",required = false) MultipartFile observedImage,
            @RequestParam("staffLogs") String staffLogs,
            @RequestParam("fieldLogs") String fieldLogs,
            @RequestParam("cropLogs") String cropLogs

    ){

        try {

            var buildDTO = new LogDTO();
            buildDTO.setDate(date);
            buildDTO.setLogDetails(logDetails);
            buildDTO.setStaffLogs(staffLogs);
            buildDTO.setFieldLogs(fieldLogs);
            buildDTO.setCropLogs(cropLogs);


            if (!observedImage.isEmpty()) {

                String base64Image1 = AppUtil.imageToBase64(observedImage.getBytes());
                buildDTO.setObservedImage(base64Image1);
            }

            logService.saveLogs(buildDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){

            e.printStackTrace();
            throw new RuntimeException("Failed to process images", e);
        }

    }

}
