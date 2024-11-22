package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.dto.impl.StaffDTO;
import lk.ijse.greenshadowbackend.exception.FieldNotFoundException;
import lk.ijse.greenshadowbackend.exception.UserNotFoundException;
import lk.ijse.greenshadowbackend.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaff(@RequestBody StaffDTO staffDTO) {
        try {
            staffService.saveStaff(staffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{staffId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStaff(@PathVariable String staffId, @RequestBody StaffDTO staffDTO) {

        try {
            staffService.updateStaff(staffId,staffDTO);
        }catch (UserNotFoundException e){
            e.printStackTrace();
        }
    }

    @DeleteMapping(value = "/{staffId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("staffId") String staffId) {

        try {
            staffService.deleteStaff(staffId);
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
    public List<StaffDTO> getAllStaff(){
        return staffService.getAllStaff();
    }
}


