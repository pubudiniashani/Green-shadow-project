package lk.ijse.greenshadowbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/staff")
public class StaffController {
    @GetMapping
    public String healthTest(){
        return "staff controller working";
    }
}