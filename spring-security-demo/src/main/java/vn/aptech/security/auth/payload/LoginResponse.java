package vn.aptech.security.auth.payload;

public record LoginResponse(String accessToken, String type, Long expiresIn) {
}
