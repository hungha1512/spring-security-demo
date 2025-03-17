package vn.aptech.security.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vn.aptech.security.auth.jwt.JWTTokenService;
import vn.aptech.security.auth.payload.LoginRequest;
import vn.aptech.security.auth.payload.LoginResponse;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JWTTokenService jwtTokenService;

    public AuthService(AuthenticationManager authenticationManager, JWTTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    public LoginResponse authenticate(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetailImpl customUserDetail = (CustomUserDetailImpl) authentication.getPrincipal();
        String accessToken = jwtTokenService.generateToken(customUserDetail);
        return new LoginResponse(accessToken, "Bearer ", jwtTokenService.getExpiration());
    }
}
