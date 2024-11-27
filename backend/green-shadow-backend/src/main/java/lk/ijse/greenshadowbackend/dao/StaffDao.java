package lk.ijse.greenshadowbackend.dao;

import lk.ijse.greenshadowbackend.entity.impl.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffDao extends JpaRepository<Staff,String> {
    Optional<Staff> findByEmail(String email);
}

