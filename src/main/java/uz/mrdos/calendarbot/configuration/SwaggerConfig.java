package uz.mrdos.calendarbot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("calendarbot adsturi bo'yicha yo'llar ")
                .description("Apilarning to'liq ro'yxati")
                .license("MRDos")
                .licenseUrl("https://mrdos.uz/")
                .version("1.0.0")
                .contact(new springfox.documentation.service.Contact("calendarbot", "https://mrdos.uz/uz", "testtest123@mail.com"))
                .build();
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("uz.dos.calendarbot"))
                .build()
                .apiInfo(apiInfo());
    }

}