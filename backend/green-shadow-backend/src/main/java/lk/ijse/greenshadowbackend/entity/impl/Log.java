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

    @ManyToOne
    @JoinColumn(name = "staffId")
    private Staff staffLogs;


    @ManyToOne
    @JoinColumn(name = "fieldId")
    private Field fieldLogs;

    @ManyToOne
    @JoinColumn(name = "cropId")
    private Crop cropLogs;


}
