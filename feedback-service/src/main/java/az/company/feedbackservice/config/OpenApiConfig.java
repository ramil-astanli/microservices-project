package az.company.feedbackservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI feedbackServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Feedback Service API")
                        .description("User Feedback Collection Service")
                        .version("v1.0.0")
                );
    }
}
