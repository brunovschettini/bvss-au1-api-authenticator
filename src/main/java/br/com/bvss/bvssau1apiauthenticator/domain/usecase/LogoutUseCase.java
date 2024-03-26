package br.com.bvss.bvssau1apiauthenticator.domain.usecase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

public interface LogoutUseCase {
    void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    );
}
