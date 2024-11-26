package lk.ijse.greenshadowbackend.service.impl;

import lk.ijse.greenshadowbackend.dao.CropDao;
import lk.ijse.greenshadowbackend.dao.FieldDao;
import lk.ijse.greenshadowbackend.dao.LogDao;
import lk.ijse.greenshadowbackend.dao.StaffDao;
import lk.ijse.greenshadowbackend.dto.impl.LogDTO;
import lk.ijse.greenshadowbackend.entity.impl.*;
import lk.ijse.greenshadowbackend.service.LogService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;
    @Autowired
    private CropDao cropDao;

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private Mapping mapping;
    @Override
    public void saveLogs(LogDTO logDTO) {
        try {
            Log log = mapping.toLogEntity(logDTO);
            Optional<Staff> staff = staffDao.findById(logDTO.getStaffLogs());
            Optional<Field> field = fieldDao.findById(logDTO.getFieldLogs());
            Optional<Crop> crop = cropDao.findById(logDTO.getCropLogs());

            if (!staff.isEmpty()){
                log.setStaffLogs(staff.get());
            }
            if (!field.isEmpty()){
                log.setFieldLogs(field.get());
            }
            if (!crop.isEmpty()){
                log.setCropLogs(crop.get());
            }
            log.setLogId(UUID.randomUUID().toString());
            logDao.save(log);
        }catch (Exception e){
            throw new RuntimeException("Error saving log",e);
        }
    }

    @Override
    public void updateLogs(String logId, LogDTO logDTO) {

    }

    @Override
    public void deleteLogs(String logId) {

    }

    @Override
    public List<LogDTO> getAllLogs() {
        return null;
    }
}
