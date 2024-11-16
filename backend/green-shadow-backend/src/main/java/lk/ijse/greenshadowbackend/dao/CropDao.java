package lk.ijse.greenshadowbackend.dao;

import lk.ijse.greenshadowbackend.entity.impl.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropDao extends JpaRepository<Crop,String> {
}
