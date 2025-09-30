package edu.fafic.msstock.shared.service;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestClient http = RestClient.create();

    private final HttpServletRequest request;

    @Value("${auth.uri}")
    private String AUTH_URI;

    public boolean validateToken(String token) {
        if (AUTH_URI == null || AUTH_URI.isBlank()) {
            log.warn("Auth URI is not defined");
            return true; // Bypass token validation if endpoint is not defined
        }

        if (token == null || token.isBlank()) {
            log.warn("No token found in request");
            return false;
        }

        URI uri = URI.create(AUTH_URI + "/auth/validate-token/");

        try {
            HttpStatusCode status = http.post()
                    .uri(uri)
                    .accept(MediaType.APPLICATION_JSON)
                    .header("Authorization", token)
                    .retrieve()
                    .toBodilessEntity()
                    .getStatusCode();

            log.info("Token validation status: {}", status);
            return status.is2xxSuccessful();
        } catch (Exception e) {
            log.warn("Error validating token", e);
            return false;
        }
    }
}
