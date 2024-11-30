package lk.ijse.greenshadowbackend.service;

import lk.ijse.greenshadowbackend.dto.impl.UserDTO;
import lk.ijse.greenshadowbackend.secure.JWTAuthResponse;
import lk.ijse.greenshadowbackend.secure.Signin;

public interface AuthService {
    JWTAuthResponse signIn(Signin signin);
    JWTAuthResponse signUp(UserDTO userDTO);
    JWTAuthResponse refreshToken(String accessToken);
}
