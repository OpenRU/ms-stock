package edu.fafic.msstock.infrastructure.service;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestClient http = RestClient.create();
    private final String MS_AUTH = "http://localhost/api/v1/"; // TODO: Inserir o endpoint da API de autenticação
    private final String VALIDATION_ENDPOINT = "validate-token/";

    private final HttpServletRequest request;

    public boolean validateToken() {
        String token = request.getHeader("Authorization");

        if (token == null || token.isBlank()) {
            return false;
        }

        String uri = MS_AUTH + VALIDATION_ENDPOINT;
        Map<String, String> body = Map.of("token", token);

        try {
            HttpStatusCode status = http.post()
                    .uri(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .toBodilessEntity()
                    .getStatusCode();

            return status.is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
