package lk.ijse.greenshadowbackend.util;

import lk.ijse.greenshadowbackend.dto.impl.*;
import lk.ijse.greenshadowbackend.entity.impl.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    //User
    public User toUserEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
    public UserDTO toUserDTO(User userEntity){
        return modelMapper.map(userEntity , UserDTO.class);
    }

    public List<UserDTO> asUserDTOlist(List<User> userEntities ){
        return modelMapper.map(userEntities , new TypeToken<List<UserDTO>>() {}.getType());
    }

    //Field

    public Field toFieldEntity(FieldDTO fieldDTO){
        return modelMapper.map(fieldDTO, Field.class);
    }
    public FieldDTO toFieldDTO(Field field){
        return modelMapper.map(field , FieldDTO.class);
    }
    public List<FieldDTO> asFieldDTOlist(List<Field> fieldEntities ){
        return modelMapper.map(fieldEntities , new TypeToken<List<FieldDTO>>() {}.getType());
    }

    //Crop

    public Crop toCropEntity(CropDTO cropDTO){
        return modelMapper.map(cropDTO, Crop.class);
    }

    public CropDTO toCropDTO(Crop crop){
        return modelMapper.map(crop , CropDTO.class);
    }

    public List<CropDTO> asCropDTOlist(List<Crop> cropEntities ){
        return modelMapper.map(cropEntities , new TypeToken<List<CropDTO>>() {}.getType());
    }

    //Staff

    public Staff toStaffEntity(StaffDTO staffDTO){
        return modelMapper.map(staffDTO, Staff.class);
    }
    public StaffDTO toStaffDTO(Staff staff){
        return modelMapper.map(staff , StaffDTO.class);
    }

    public List<StaffDTO> asStaffDTOlist(List<Staff> staffEntities ){
        return modelMapper.map(staffEntities , new TypeToken<List<StaffDTO>>() {}.getType());
    }

    //Equipment

    public Equipment toEquipmentEntity(EquipmentDTO equipmentDTO){
        return modelMapper.map(equipmentDTO, Equipment.class);
    }
    public EquipmentDTO toEquipmentDTO(Equipment equipment){
        return modelMapper.map(equipment , EquipmentDTO.class);
    }

    public List<EquipmentDTO> asEquipmentDTOlist(List<Equipment> equipmentEntities ){
        return modelMapper.map(equipmentEntities , new TypeToken<List<EquipmentDTO>>() {}.getType());
    }

    //log

    public Log toLogEntity(LogDTO logDTO){
        return modelMapper.map(logDTO, Log.class);
    }
    public LogDTO toLogDTO(Log log){
        return modelMapper.map(log , LogDTO.class);
    }

   /* public List<LogDTO> asLogDTOlist(List<Log> logEntities ){
        return modelMapper.map(logEntities , new TypeToken<List<LogDTO>>() {}.getType());
    }*/

    public List<LogDTO> asLogDTOlist(List<Log> logEntities) {
        List<LogDTO> logDTOs = new ArrayList<>();
        for (Log log : logEntities) {
            LogDTO dto = new LogDTO();
            dto.setLogId(log.getLogId());
            dto.setDate(log.getDate());
            dto.setLogDetails(log.getLogDetails());
            dto.setObservedImage(log.getObservedImage());
            

            if (log.getStaffLogs() != null) {
                dto.setStaffLogs(log.getStaffLogs().getStaffId());
            }

            // Set fieldId
            if (log.getFieldLogs() != null) {
                dto.setFieldLogs(log.getFieldLogs().getFieldId());
            }
            if (log.getCropLogs() != null) {
                dto.setCropLogs(log.getCropLogs().getCropId());
            }

            logDTOs.add(dto);
        }
        return logDTOs;
    }

    //Vehicle

    public Vehicle toVehicleEntity(VehicleDTO vehicleDTO){
        return modelMapper.map(vehicleDTO, Vehicle.class);
    }
    public VehicleDTO toVehicleDTO(Vehicle vehicle){
        return modelMapper.map(vehicle , VehicleDTO.class);
    }

    public List<VehicleDTO> asVehicleDTOlist(List<Vehicle> vehicleEntities ){
        return modelMapper.map(vehicleEntities , new TypeToken<List<VehicleDTO>>() {}.getType());
    }

}
