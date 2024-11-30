package lk.ijse.greenshadowbackend.service.impl;

import lk.ijse.greenshadowbackend.dao.UserDao;
import lk.ijse.greenshadowbackend.dto.impl.UserDTO;
import lk.ijse.greenshadowbackend.secure.JWTAuthResponse;
import lk.ijse.greenshadowbackend.secure.Signin;
import lk.ijse.greenshadowbackend.service.AuthService;
import lk.ijse.greenshadowbackend.service.JWTService;
import lk.ijse.greenshadowbackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserDao userDao;

    private final Mapping mapping;

    private final JWTService jwtService;

    @Override
    public JWTAuthResponse signIn(Signin signin) {
        return null;
    }

    @Override
    public JWTAuthResponse signUp(UserDTO userDTO) {
        return null;
    }

    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
        return null;
    }
}
