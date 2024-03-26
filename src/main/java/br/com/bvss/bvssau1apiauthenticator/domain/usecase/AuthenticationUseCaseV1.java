package br.com.bvss.bvssau1apiauthenticator.domain.usecase;

import br.com.bvss.bvssau1apiauthenticator.domain.dtos.request.AuthenticationRequest;
import br.com.bvss.bvssau1apiauthenticator.domain.dtos.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationUseCaseV1 {

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
