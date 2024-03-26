package br.com.bvss.bvssau1apiauthenticator.domain.usecase.impl;


import br.com.bvss.bvssau1apiauthenticator.domain.repositories.sql.UserRepository;
import br.com.bvss.bvssau1apiauthenticator.domain.dtos.request.AuthenticationRequest;
import br.com.bvss.bvssau1apiauthenticator.domain.dtos.response.AuthenticationResponse;
import br.com.bvss.bvssau1apiauthenticator.domain.entities.TokenEntity;
import br.com.bvss.bvssau1apiauthenticator.domain.entities.UserEntity;
import br.com.bvss.bvssau1apiauthenticator.domain.enums.TokenTypeEnum;
import br.com.bvss.bvssau1apiauthenticator.domain.repositories.sql.TokenRepository;
import br.com.bvss.bvssau1apiauthenticator.domain.usecase.AuthenticationUseCaseV1;
import br.com.bvss.bvssau1apiauthenticator.domain.usecase.JwtUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Qualifier("v1")
public class AuthenticationUseCaseImplV1 implements AuthenticationUseCaseV1 {
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private JwtUseCase jwtUseCase;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse authenticate(final AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtUseCase.generateToken(user);
        var refreshToken = jwtUseCase.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(final UserEntity user, final String jwtToken) {
        var token = TokenEntity.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenTypeEnum.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(final UserEntity user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            final HttpServletRequest request,
            final HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtUseCase.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtUseCase.isTokenValid(refreshToken, user)) {
                var accessToken = jwtUseCase.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}