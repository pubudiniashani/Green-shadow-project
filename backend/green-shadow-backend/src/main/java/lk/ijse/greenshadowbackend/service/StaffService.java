package lk.ijse.greenshadowbackend.service;


import lk.ijse.greenshadowbackend.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);
    void updateStaff(String staffId, StaffDTO staffDTO);
    void deleteStaff(String staffId);
    List<StaffDTO> getAllStaff();
}
