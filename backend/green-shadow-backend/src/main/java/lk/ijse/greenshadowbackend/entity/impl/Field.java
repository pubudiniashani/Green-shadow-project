package lk.ijse.greenshadowbackend.entity.impl;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "field")
public class Field {

    @Id
    private String fieldId;
    private String name;
    private String location;
    private double extentSize;
    @Column(columnDefinition = "LONGTEXT")
    private String image1;
    @Column(columnDefinition = "LONGTEXT")
    private String image2;

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getExtentSize() {
        return extentSize;
    }

    public void setExtentSize(double extentSize) {
        this.extentSize = extentSize;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public List<Crop> getCrops() {
        return crops;
    }

    public void setCrops(List<Crop> crops) {
        this.crops = crops;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    @OneToMany(mappedBy = "field",cascade = CascadeType.ALL)
    private List<Crop> crops;

    @ManyToMany(mappedBy = "fields",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Staff> staff = new ArrayList<>();

    @OneToMany(mappedBy = "fieldLogs",cascade = CascadeType.ALL)
    private List<Log> logs;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Equipment> equipment;
}
