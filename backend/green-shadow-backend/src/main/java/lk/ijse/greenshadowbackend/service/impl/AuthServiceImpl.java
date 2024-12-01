package lk.ijse.greenshadowbackend.service.impl;

import lk.ijse.greenshadowbackend.dao.UserDao;
import lk.ijse.greenshadowbackend.dto.impl.UserDTO;
import lk.ijse.greenshadowbackend.entity.impl.User;
import lk.ijse.greenshadowbackend.secure.JWTAuthResponse;
import lk.ijse.greenshadowbackend.secure.Signin;
import lk.ijse.greenshadowbackend.service.AuthService;
import lk.ijse.greenshadowbackend.service.JWTService;
import lk.ijse.greenshadowbackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserDao userDao;

    private final Mapping mapping;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    @Override
    public JWTAuthResponse signIn(Signin signin) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signin.getEmail(),signin.getPassword()));
        var user = userDao.findByEmail(signin.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        var generatedToken = jwtService.generateToken(user);
        return JWTAuthResponse.builder().token(generatedToken).build();
    }

    @Override
    public JWTAuthResponse signUp(UserDTO userDTO) {
        /*User saveUser = userDao.save(mapping.toUserEntity(userDTO));

        var generatedToken =  jwtService.generateToken(saveUser);
        return  JWTAuthResponse.builder().token(generatedToken).build();*/

        userDTO.setUserId(UUID.randomUUID().toString());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        //save user
        User user = mapping.toUserEntity(userDTO);
        System.out.println(user);
        User savedUser = userDao.save(user);
        // System.out.println(savedUser);
        //generate token
        var token = jwtService.generateToken(savedUser);
        return JWTAuthResponse.builder().token(token).build();

    }
    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
        var userName = jwtService.extractUserName(accessToken);

        var findUser = userDao.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var refreshToken = jwtService.refreshToken(findUser);
        return JWTAuthResponse.builder().token(refreshToken).build();
    }
}



