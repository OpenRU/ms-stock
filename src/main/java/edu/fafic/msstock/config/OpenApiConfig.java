package edu.fafic.msstock.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "MS-Stock API",
                version = "v1",
                description = "Microsserviço de Controle de Estoque e Insumos da OpenRU"
        ),
        security = @SecurityRequirement(name = "lonlonToken")
)
@SecurityScheme(
        name = "lonlonToken",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER,
        paramName = "Authorization",
        description = "Token proprietário enviado no header Authorization"
)
@Configuration
public class OpenApiConfig {
}
