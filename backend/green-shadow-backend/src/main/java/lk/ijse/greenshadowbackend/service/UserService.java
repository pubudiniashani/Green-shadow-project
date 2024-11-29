package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.impl.UserDTO;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    void deleteUser(String userId);
    void updateUser(String userId , UserDTO userDTO);
    List<UserDTO> getAllUsers();


}
