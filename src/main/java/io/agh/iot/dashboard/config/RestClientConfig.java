package io.agh.iot.dashboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    private final JwtForwardingInterceptor jwtForwardingInterceptor;

    public RestClientConfig(JwtForwardingInterceptor jwtForwardingInterceptor) {
        this.jwtForwardingInterceptor = jwtForwardingInterceptor;
    }


    @Bean
    RestClient deviceApiClient(RestClient.Builder builder, DownstreamProperties properties) {
        return builder.baseUrl(properties.getDeviceApiBaseUrl()).build();
    }


    @Bean
    RestClient streamWorkerClient(RestClient.Builder builder, DownstreamProperties properties) {
        return builder.baseUrl(properties.getStreamWorkerBaseUrl())
                .requestInterceptor(jwtForwardingInterceptor)
                .build();
    }

    @Bean
    RestClient alertApiClient(RestClient.Builder builder, DownstreamProperties properties) {
        return builder.baseUrl(properties.getAlertApiBaseUrl())
                .requestInterceptor(jwtForwardingInterceptor)
                .build();
    }
}
