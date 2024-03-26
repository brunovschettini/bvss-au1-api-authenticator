package br.com.bvss.bvssau1apiauthenticator.entrypoins.rest.controllers;

import br.com.bvss.bvssau1apiauthenticator.domain.dtos.request.AuthenticationRequest;
import br.com.bvss.bvssau1apiauthenticator.domain.dtos.response.AuthenticationResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Tag(name = "Autenticação")
@RequestMapping("/v1")
public interface AuthenticationControllerV1 {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrou produto",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuthenticationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "id_produto inválido.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado.",
                    content = @Content)
    })
    @PostMapping("/authenticate")
    ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrou produto",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuthenticationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "id_produto inválido.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado.",
                    content = @Content)
    })
    @PostMapping("/refresh-token")
    void refreshToken(HttpServletRequest request,
                      HttpServletResponse response) throws IOException;


}