package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.impl.UserDTO;

public interface UserService {
    void saveUser(UserDTO userDTO);
    void deleteUser(String userId);
    void updateUser(String userId , UserDTO userDTO);


}
