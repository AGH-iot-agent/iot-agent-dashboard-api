package io.agh.iot.dashboard.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    @Qualifier("deviceApiClient")
    RestClient deviceApiClient(RestClient.Builder builder, DownstreamProperties properties) {
        return builder.baseUrl(properties.getDeviceApiBaseUrl()).build();
    }

    @Bean
    @Qualifier("streamWorkerClient")
    RestClient streamWorkerClient(RestClient.Builder builder, DownstreamProperties properties) {
        return builder.baseUrl(properties.getStreamWorkerBaseUrl()).build();
    }

    @Bean
    @Qualifier("alertApiClient")
    RestClient alertApiClient(RestClient.Builder builder, DownstreamProperties properties) {
        return builder.baseUrl(properties.getAlertApiBaseUrl()).build();
    }
}
