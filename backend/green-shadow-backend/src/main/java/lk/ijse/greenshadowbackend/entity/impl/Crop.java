package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "crop")
public class Crop {
    @Id
    private String cropId;
    private String commonName;
    private String scientificName;
    private String category;
    private String season;
    @Column(columnDefinition = "LONGTEXT")
    private String cropImage;

    @ManyToOne
    @JoinColumn(name = "fieldId")
    private Field field;

    @OneToMany(mappedBy = "cropLogs",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Log> logs;
}
