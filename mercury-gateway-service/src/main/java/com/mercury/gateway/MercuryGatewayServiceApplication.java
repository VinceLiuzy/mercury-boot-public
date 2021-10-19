package com.mercury.gateway;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(
        title = "Mercury网关",
        version = "1.0.0",
        description = "Mercury Boot基础版",
        license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0"),
        contact = @Contact(name = "LiuZhengYu", url = "https://github.com/VinceLiuzy", email = "vince.liuzy@outlook.com")
))
public class MercuryGatewayServiceApplication {
    @Autowired
    RouteDefinitionLocator locator;

    @Autowired
    SwaggerUiConfigParameters swaggerUiConfigParameters;

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext context = SpringApplication.run(MercuryGatewayServiceApplication.class, args);

        Environment env = context.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        path = path != null ? path : "";

        log.info("\n----------------------------------------------------------\n\t"
                + "Mercury Gateway is running! Access URLs:\n\t" + "Local: \t\thttp://localhost:" + port + path
                + "/\n\t" + "External: \thttp://" + ip + ":" + port + path + "/\n\t" + "swagger-ui: \thttp://" + ip
                + ":" + port + path + "/swagger-ui.html\n" + "----------------------------------------------------------");
    }

    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis() {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();

        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
                .forEach(routeDefinition -> {
                    String name = routeDefinition.getId().replaceAll("-service", "");
                    swaggerUiConfigParameters.addGroup(name);
                    GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
                });

        return groups;
    }
}
