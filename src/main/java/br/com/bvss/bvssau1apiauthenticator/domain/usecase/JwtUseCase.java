package br.com.bvss.bvssau1apiauthenticator.domain.usecase;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Map;
import java.util.function.Function;

public interface JwtUseCase {

    String extractUsername(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String generateToken(UserDetails userDetails);

    String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    );

    String generateRefreshToken(
            UserDetails userDetails
    );


    boolean isTokenValid(String token, UserDetails userDetails);
}