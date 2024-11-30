package lk.ijse.greenshadowbackend.service.impl;

import lk.ijse.greenshadowbackend.dao.StaffDao;
import lk.ijse.greenshadowbackend.dao.UserDao;
import lk.ijse.greenshadowbackend.dto.impl.UserDTO;
import lk.ijse.greenshadowbackend.entity.impl.Staff;
import lk.ijse.greenshadowbackend.entity.impl.User;
import lk.ijse.greenshadowbackend.exception.UserNotFoundException;
import lk.ijse.greenshadowbackend.service.UserService;
import lk.ijse.greenshadowbackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveUser(UserDTO userDTO) {
        /*try {

            User user = mapping.toUserEntity(userDTO);
            user.setUserId(UUID.randomUUID().toString());
            userDao.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error saving user", e);
        }*/

        try {

            User user = mapping.toUserEntity(userDTO);
            user.setUserId(UUID.randomUUID().toString());

            Optional<Staff> existingStaff = staffDao.findByEmail(user.getEmail());

            Staff staff;
            if (existingStaff.isPresent()) {

                staff = existingStaff.get();
            } else {

                staff = new Staff();
                staff.setStaffId(UUID.randomUUID().toString());
                staff.setEmail(user.getEmail());
                staff.setRole(user.getRole());
                staffDao.save(staff);
            }

            user.setStaff(staff);

            userDao.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error saving user", e);
        }

    }

    @Override
    public void deleteUser(String userId) {
        Optional<User> existedUser=  userDao.findById(userId);
        if (!existedUser.isPresent()){
            throw new UserNotFoundException("User with this ID is not found");
        }else {
            userDao.deleteById(userId);
        }
    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {
        Optional<User> tmpUser = userDao.findById(userId);

        if (tmpUser.isPresent()){
            tmpUser.get().setEmail(userDTO.getEmail());
            tmpUser.get().setPassword(userDTO.getPassword());
            tmpUser.get().setRole(userDTO.getRole());

        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> allUsers = userDao.findAll();
        return mapping.asUserDTOlist(allUsers);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return null;
    }
}
