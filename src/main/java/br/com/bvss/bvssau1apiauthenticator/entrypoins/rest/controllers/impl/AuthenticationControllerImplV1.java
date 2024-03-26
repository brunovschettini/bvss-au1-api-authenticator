package br.com.bvss.bvssau1apiauthenticator.entrypoins.rest.controllers.impl;

// https://github.com/ali-bouali/spring-boot-3-jwt-security/blob/main/src/main/java/com/alibou/security/token/Token.java

import br.com.bvss.bvssau1apiauthenticator.domain.usecase.AuthenticationUseCaseV1;
import br.com.bvss.bvssau1apiauthenticator.entrypoins.rest.controllers.AuthenticationControllerV1;
import br.com.bvss.bvssau1apiauthenticator.domain.dtos.request.AuthenticationRequest;
import br.com.bvss.bvssau1apiauthenticator.domain.dtos.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthenticationControllerImplV1 implements AuthenticationControllerV1 {

    @Autowired
    @Qualifier("v1")
    private AuthenticationUseCaseV1 authenticationUseCaseV1;

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(
            final @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationUseCaseV1.authenticate(request));
    }

    @Override
    public void refreshToken(
            final HttpServletRequest request,
            final HttpServletResponse response
    ) throws IOException {
        authenticationUseCaseV1.refreshToken(request, response);
    }

}