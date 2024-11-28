package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "log")
public class Log {
    @Id
    private String logId;
    private Date date;
    private String logDetails;
    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;

    @ManyToOne
    @JoinColumn(name = "staffId")
    private Staff staffLogs;

    @ManyToOne
    @JoinColumn(name = "fieldId")
    private Field fieldLogs;

    @ManyToOne
    @JoinColumn(name = "cropId")
    private Crop cropLogs;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLogDetails() {
        return logDetails;
    }

    public void setLogDetails(String logDetails) {
        this.logDetails = logDetails;
    }

    public String getObservedImage() {
        return observedImage;
    }

    public void setObservedImage(String observedImage) {
        this.observedImage = observedImage;
    }

    public Staff getStaffLogs() {
        return staffLogs;
    }

    public void setStaffLogs(Staff staffLogs) {
        this.staffLogs = staffLogs;
    }

    public Field getFieldLogs() {
        return fieldLogs;
    }

    public void setFieldLogs(Field fieldLogs) {
        this.fieldLogs = fieldLogs;
    }

    public Crop getCropLogs() {
        return cropLogs;
    }

    public void setCropLogs(Crop cropLogs) {
        this.cropLogs = cropLogs;
    }
}
