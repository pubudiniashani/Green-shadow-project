package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@RestController
@RequestMapping("api/v1/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveUser(
            @RequestPart("fieldId") String fieldId,
            @RequestPart("name") String name,
            @RequestPart("location")Point location,
            @RequestPart("extentSize") double extentSize,
            @RequestPart("image1")MultipartFile image1,
            @RequestPart("image2") MultipartFile image2
            ){

    }



}
