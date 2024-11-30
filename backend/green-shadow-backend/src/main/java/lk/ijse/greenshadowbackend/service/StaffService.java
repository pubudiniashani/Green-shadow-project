package lk.ijse.greenshadowbackend.service;


import lk.ijse.greenshadowbackend.dto.impl.StaffDTO;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);
    void updateStaff(String staffId, StaffDTO staffDTO);
    void deleteStaff(String staffId);
    List<StaffDTO> getAllStaff();
    Optional<StaffDTO> findByEmail(String email);
}
