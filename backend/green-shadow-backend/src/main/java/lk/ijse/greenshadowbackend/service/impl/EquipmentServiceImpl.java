package lk.ijse.greenshadowbackend.service.impl;

import lk.ijse.greenshadowbackend.dao.EquipmentDao;
import lk.ijse.greenshadowbackend.dao.FieldDao;
import lk.ijse.greenshadowbackend.dao.StaffDao;
import lk.ijse.greenshadowbackend.dto.impl.EquipmentDTO;
import lk.ijse.greenshadowbackend.entity.impl.Equipment;
import lk.ijse.greenshadowbackend.entity.impl.Field;
import lk.ijse.greenshadowbackend.entity.impl.Staff;
import lk.ijse.greenshadowbackend.service.EquipmentService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        try {
            Equipment equipment = mapping.toEquipmentEntity(equipmentDTO);
            equipment.setEquipmentId(UUID.randomUUID().toString());

           /* Optional<Field> field = fieldDao.findById(equipmentDTO.getField());
            Optional<Staff> staff = staffDao.findById(equipmentDTO.getStaff());

            if (!field.isEmpty() && !staff.isEmpty()){
                equipment.setField(field.get());
                equipment.setStaff(staff.get());
            }*/

            Field field = fieldDao.findById(equipmentDTO.getField())
                    .orElseThrow(() -> new RuntimeException("Field not found for ID: " + equipmentDTO.getField()));

            Staff staff = staffDao.findById(equipmentDTO.getStaff())
                    .orElseThrow(() -> new RuntimeException("Staff not found for ID: " + equipmentDTO.getStaff()));

            equipment.setField(field);
            equipment.setStaff(staff);
            equipmentDao.save(equipment);
        }catch (Exception e){
            throw new RuntimeException("Error saving crop",e);
        }

    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {

    }

    @Override
    public void deleteEquipment(String equipmentId) {

    }

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        return null;
    }
}
