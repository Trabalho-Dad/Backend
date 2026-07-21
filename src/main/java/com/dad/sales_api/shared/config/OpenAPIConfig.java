package com.dad.sales_api.shared.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    tags = {
        @Tag(name = "Auth", description = "Autenticação de usuários."),
        @Tag(name = "User", description = "Rotas para visualização e edição do usuário logado"),
        @Tag(name = "Address", description = "Informações sobre os endereços do meu usuário"),
        @Tag(name = "Order", description = "Rotas para CRUD de carrinhos/pedidos"),
        @Tag(name = "Figure", description = "Rotas públicas para visualização dos bonecos"),
        @Tag(name = "Category", description = "Rotas públicas para visualização das categorias")
    }
)
public class OpenAPIConfig {

  @Bean
  OpenAPI customOpenAPI(){
    return new OpenAPI()
        .info(
            new Info()
                .title("Astra API")
                .version("v1")
                .description("Aqui você encontra todas as rotas da API e o que é necessário enviar de parâmetros para elas funcionarem.")
                .termsOfService("")
                .license(
                    new License()
                        .name("Apache 2.0")
                        .url("")
                )
        ).addSecurityItem(new SecurityRequirement().addList(SecurityConfig.SECURITY))
        .components(
            new Components()
                .addSecuritySchemes(
                    SecurityConfig.SECURITY,
                    new SecurityScheme()
                        .name(SecurityConfig.SECURITY)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
        );
  }
}