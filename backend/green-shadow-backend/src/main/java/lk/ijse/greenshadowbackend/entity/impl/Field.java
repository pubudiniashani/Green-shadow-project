package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
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

    @OneToMany(mappedBy = "field",cascade = CascadeType.ALL)
    private List<Crop> crops;

    @ManyToMany(mappedBy = "fields",cascade = CascadeType.ALL)
    private List<Staff> staff = new ArrayList<>();

    @ManyToMany(mappedBy = "fieldLogs",cascade = CascadeType.ALL)
    private List<Log> logs;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Equipment> equipment;
}
