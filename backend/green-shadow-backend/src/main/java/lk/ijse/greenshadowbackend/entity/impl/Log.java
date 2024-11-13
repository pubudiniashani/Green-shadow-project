package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "log")
public class Log {
    @Id
    private String logId;
    private Date date;
    private String logDetails;
    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;

    @ManyToMany
    @JoinTable(
            name = "staff_logs_details",
            joinColumns = @JoinColumn(name = "log_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    private List<Staff> staffLogs;


    @ManyToMany
    @JoinTable(
            name = "field_logs",
            joinColumns = @JoinColumn(name = "log_id"),
            inverseJoinColumns = @JoinColumn(name = "field_id")
    )
    private List<Field> fieldLogs;

    @ManyToMany
    @JoinTable(
            name = "crop_logs",
            joinColumns = @JoinColumn(name = "log_id"),
            inverseJoinColumns = @JoinColumn(name = "crop_id")
    )
    private List<Crop> cropLogs;


}
