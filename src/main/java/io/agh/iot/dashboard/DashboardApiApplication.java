package io.agh.iot.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import io.agh.iot.dashboard.config.DownstreamProperties;

@SpringBootApplication
@EnableConfigurationProperties(DownstreamProperties.class)
public class DashboardApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DashboardApiApplication.class, args);
    }
}
