package lk.ijse.greenshadowbackend.controller;

import lk.ijse.greenshadowbackend.dto.impl.StaffDTO;
import lk.ijse.greenshadowbackend.dto.impl.UserDTO;
import lk.ijse.greenshadowbackend.entity.impl.Staff;
import lk.ijse.greenshadowbackend.secure.JWTAuthResponse;
import lk.ijse.greenshadowbackend.secure.Signin;
import lk.ijse.greenshadowbackend.service.AuthService;
import lk.ijse.greenshadowbackend.service.StaffService;
import lk.ijse.greenshadowbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    private final StaffService staffService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "signup",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> saveUser(@RequestBody UserDTO userDTO){
       /* System.out.println(userDTO);
        try {
            Optional<StaffDTO> existStaff = staffService.findByEmail(userDTO.getEmail());

            if (!existStaff.isPresent()){
                StaffDTO staff = new StaffDTO();
                staff.setEmail(userDTO.getEmail());
                staff.setRole(userDTO.getRole());

                staffService.saveStaff(staff);


            }else {
                userDTO.setStaff(existStaff.get().getStaffId());
                userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }

            userDTO.setUserId(UUID.randomUUID().toString());
            return ResponseEntity.ok(authService.signUp(userDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }*/

        System.out.println(userDTO);
        try {

            userDTO.setUserId(UUID.randomUUID().toString());

            Optional<StaffDTO> existingStaff = staffService.findByEmail(userDTO.getEmail());

            if (!existingStaff.isPresent()) {

                StaffDTO newStaff = new StaffDTO();
                newStaff.setEmail(userDTO.getEmail());
                newStaff.setRole(userDTO.getRole());

                String staffId = staffService.saveStaff(newStaff).getStaffId();
                userDTO.setStaff(staffId);


                userDTO.setStaff(newStaff.getStaffId());

            } else {

                userDTO.setStaff(existingStaff.get().getStaffId());
            }



            return ResponseEntity.ok(authService.signUp(userDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "signin",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody Signin signIn){

        return ResponseEntity.ok(authService.signIn(signIn));

    }
    @PostMapping("refresh")
    public ResponseEntity<JWTAuthResponse> refreshToken(@RequestParam("refreshToken") String existingToken) {
        //token ek expire wela nn ayem token ekka argnna
        return ResponseEntity.ok(authService.refreshToken(existingToken));

    }


}
