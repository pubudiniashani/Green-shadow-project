package lk.ijse.greenshadowbackend.service.impl;

import lk.ijse.greenshadowbackend.service.JWTService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JWTServiceImpl implements JWTService {
    @Override
    public String extractUserName(String token) {
        return null;
    }

    @Override
    public String generateToken(UserDetails user) {
        return null;
    }

    @Override
    public boolean validateToken(String token, UserDetails user) {
        return false;
    }

    @Override
    public String refreshToken(UserDetails userDetails) {
        return null;
    }
}
