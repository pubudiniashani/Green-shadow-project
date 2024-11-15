package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.dto.StaffStatus;
import lk.ijse.greenshadowbackend.entity.Gender;
import lk.ijse.greenshadowbackend.entity.Role;
import lk.ijse.greenshadowbackend.entity.impl.Field;
import lk.ijse.greenshadowbackend.entity.impl.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements StaffStatus {
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    private Gender gender;
    private Date jointedDate;
    private Date dob;
    private String address;
    private String contactNumber;
    private Role role;
    private List<Field> fields;
    private List<Vehicle> vehicles;
}
