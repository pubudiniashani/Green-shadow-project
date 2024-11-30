package lk.ijse.greenshadowbackend.service.impl;

import lk.ijse.greenshadowbackend.dao.FieldDao;
import lk.ijse.greenshadowbackend.dao.StaffDao;
import lk.ijse.greenshadowbackend.dto.impl.StaffDTO;
import lk.ijse.greenshadowbackend.entity.impl.Field;
import lk.ijse.greenshadowbackend.entity.impl.Staff;
import lk.ijse.greenshadowbackend.exception.UserNotFoundException;
import lk.ijse.greenshadowbackend.service.StaffService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveStaff(StaffDTO staffDTO) {
       /* try {

            Staff staff = mapping.toStaffEntity(staffDTO);
            staff.setStaffId(UUID.randomUUID().toString());
            staffDao.save(staff);
        } catch (Exception e) {
            throw new RuntimeException("Error saving user", e);
        }*/

        try {
            Staff staffEntity = mapping.toStaffEntity(staffDTO);
            staffEntity.setStaffId(UUID.randomUUID().toString());
            //System.out.println("dto - " + staffDTO);

            if (staffDTO.getFields() != null && !staffDTO.getFields().isEmpty()) {
                List<Field> associatedFields = new ArrayList<>();
                for (String fieldId : staffDTO.getFields()) {
                    Field field = fieldDao.findById(fieldId)
                            .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + fieldId));
                    associatedFields.add(field);
                }
                staffEntity.setFields(associatedFields);
                //System.out.println("staffEntity - " + staffEntity);
            }

            staffDao.save(staffEntity);

        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving user", e);
        }
    }

    @Override
    public void updateStaff(String staffId, StaffDTO staffDTO) {
       /* Optional<Staff> tmpStaff = staffDao.findById(staffId);

        if (tmpStaff.isPresent()){
            tmpStaff.get().setFirstName(staffDTO.getFirstName());
            tmpStaff.get().setLastName(staffDTO.getLastName());
            tmpStaff.get().setDesignation(staffDTO.getDesignation());
            tmpStaff.get().setGender(staffDTO.getGender());
            tmpStaff.get().setJointedDate(staffDTO.getJointedDate());
            tmpStaff.get().setDob(staffDTO.getDob());
            tmpStaff.get().setAddress(staffDTO.getAddress());
            tmpStaff.get().setContactNumber(staffDTO.getContactNumber());
            tmpStaff.get().setEmail(staffDTO.getEmail());
            tmpStaff.get().setRole(staffDTO.getRole());

        }*/

        Staff tmpStaff = staffDao.findById(staffId)
                .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + staffId));

        tmpStaff.setFirstName(staffDTO.getFirstName());
        tmpStaff.setLastName(staffDTO.getLastName());
        tmpStaff.setDesignation(staffDTO.getDesignation());
        tmpStaff.setGender(staffDTO.getGender());
        tmpStaff.setJointedDate(staffDTO.getJointedDate());
        tmpStaff.setDob(staffDTO.getDob());
        tmpStaff.setAddress(staffDTO.getAddress());
        tmpStaff.setContactNumber(staffDTO.getContactNumber());
        tmpStaff.setEmail(staffDTO.getEmail());
        tmpStaff.setRole(staffDTO.getRole());

        if (staffDTO.getFields() != null && !staffDTO.getFields().isEmpty()) {
            tmpStaff.getFields().clear();

            for (String fieldId:staffDTO.getFields()) {
                Field field = fieldDao.findById(fieldId)
                        .orElseThrow(() -> new IllegalArgumentException("field not found " ));

                if (!field.getStaff().contains(tmpStaff)) {
                    field.getStaff().add(tmpStaff);
                }

                tmpStaff.getFields().add(field);
            }

        }else {
            tmpStaff.getFields().clear();
        }

        staffDao.save(tmpStaff);
    }

    @Override
    public void deleteStaff(String staffId) {
        Optional<Staff> existedStaff =  staffDao.findById(staffId);
        if (!existedStaff.isPresent()){
            throw new UserNotFoundException("User with this ID is not found");
        }else {
            staffDao.deleteById(staffId);
        }
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        //List<Staff> allStaff = staffDao.findAll();
        return mapping.asStaffDTOlist(staffDao.findAll());
    }

    @Override
    public Optional<StaffDTO> findByEmail(String email) {
        Optional<Staff> byEmail = staffDao.findByEmail(email);

        if (byEmail.isPresent()) {
            StaffDTO staffDto = mapping.toStaffDTO(byEmail.get());
            return Optional.of(staffDto);
        }

        return Optional.empty();
    }
}
