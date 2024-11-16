package lk.ijse.greenshadowbackend.dao;

import lk.ijse.greenshadowbackend.entity.impl.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldDao extends JpaRepository<Field,String> {
}
