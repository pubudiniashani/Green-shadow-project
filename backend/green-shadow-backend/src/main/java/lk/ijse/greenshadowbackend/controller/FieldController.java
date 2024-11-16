package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;





}
