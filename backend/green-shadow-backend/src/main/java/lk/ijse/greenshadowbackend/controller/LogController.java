package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.dto.impl.LogDTO;
import lk.ijse.greenshadowbackend.exception.FieldNotFoundException;
import lk.ijse.greenshadowbackend.service.LogService;
import lk.ijse.greenshadowbackend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/logs")
@CrossOrigin
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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{logId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateLog(
            @RequestParam("date") Date date,
            @RequestParam("logDetails") String logDetails,
            @RequestParam(value = "observedImage",required = false) MultipartFile observedImage,
            @RequestParam("staffLogs") String staffLogs,
            @RequestParam("fieldLogs") String fieldLogs,
            @RequestParam("cropLogs") String cropLogs,

            @PathVariable("logId") String logId
    ) {
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
            logService.updateLogs(logId,buildDTO);
        }catch (Exception e){

            e.printStackTrace();
            throw new RuntimeException("Failed to process images", e);
        }

    }

    @DeleteMapping(value = "/{logId}")
    public ResponseEntity<Void> deleteLog(@PathVariable("logId") String logId) {

        try {
            logService.deleteLogs(logId);
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
    public List<LogDTO> getAllLogs(){
        return logService.getAllLogs();
    }

    @GetMapping(value = "/{logId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LogDTO> getCropById(@PathVariable ("logId") String logId){
        LogDTO logDTO = logService.findById(logId);
        if (logDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(logDTO,HttpStatus.OK);
    }

}

