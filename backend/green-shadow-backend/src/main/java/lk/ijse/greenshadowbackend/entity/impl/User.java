package lk.ijse.greenshadowbackend.entity.impl;

import jakarta.persistence.*;
import lk.ijse.greenshadowbackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    private String userId;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


}
