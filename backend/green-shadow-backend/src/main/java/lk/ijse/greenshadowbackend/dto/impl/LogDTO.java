package lk.ijse.greenshadowbackend.dto.impl;

import lk.ijse.greenshadowbackend.dto.LogStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogDTO implements LogStatus {
    private String logId;
    private Date date;
    private String logDetails;
    private String observedImage;
}
