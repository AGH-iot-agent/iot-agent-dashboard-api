package io.agh.iot.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class DevicesController {

    private final RestClient deviceApiClient;

    public DevicesController(@Qualifier("deviceApiClient") RestClient deviceApiClient) {
        this.deviceApiClient = deviceApiClient;
    }

    @GetMapping("/api/devices")
    public List<JsonNode> getDevices() {
        try {
            JsonNode[] devices = deviceApiClient.get().uri("/devices").retrieve().body(JsonNode[].class);
            if (devices == null) {
                return List.of();
            }
            return List.of(devices);
        } catch (RestClientException exception) {
            return List.of();
        }
    }
}
