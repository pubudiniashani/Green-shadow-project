package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

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

}
