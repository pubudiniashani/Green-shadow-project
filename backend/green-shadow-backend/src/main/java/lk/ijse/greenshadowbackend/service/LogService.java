package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.impl.LogDTO;


import java.util.List;

public interface LogService {

    void saveLogs(LogDTO logDTO);
    void updateLogs(String logId, LogDTO logDTO);
    void deleteLogs(String logId);
    List<LogDTO> getAllLogs();
    LogDTO findById(String logId);
}
