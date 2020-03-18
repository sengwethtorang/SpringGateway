package com.example.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

	@Value("${gateway_path}")
	private List<String> gateway_path;
	
	@Value("${gateway_url}")
	private List<String> gateway_url;
	
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {

    	RouteLocatorBuilder.Builder routeLocator = builder.routes();
    	for(int i =0 ;i<gateway_path.size() ; i++) {
    		
    		String path = gateway_path.get(i);
    		String url = gateway_url.get(i);
    		routeLocator.route(r -> r.path(path+"/**")
                    .filters(f->f.rewritePath(path+"/(?<segment>.*)", "/${segment}"))
                    .uri(url));
        }
        return routeLocator.build();
    	
    }

}