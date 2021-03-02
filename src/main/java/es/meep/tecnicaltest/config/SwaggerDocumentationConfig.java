package es.meep.tecnicaltest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-23T21:54:32.050Z[GMT]")
@Configuration
public class SwaggerDocumentationConfig {
	
	ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("app_alerts_back ").description(
				"Back end services for app alerts, all endpoints validate the validity of the subscription and that the token is correct, all /me service query with the user token information and context")
				.license("").licenseUrl("http://unlicense.org").termsOfServiceUrl("").version(getClass().getPackage().getImplementationVersion())
				.contact(new Contact("", "", "")).build();
	}

	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("es.meep.tecnicaltest.controller")).build()
				.directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
				.directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class).apiInfo(apiInfo());
	}

}
