package io.agh.iot.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class TelemetryController {

    private final RestClient streamWorkerClient;
    private final RestClient alertApiClient;

    public TelemetryController(@Qualifier("streamWorkerClient") RestClient streamWorkerClient,
                               @Qualifier("alertApiClient") RestClient alertApiClient) {
        this.streamWorkerClient = streamWorkerClient;
        this.alertApiClient = alertApiClient;
    }

    @GetMapping("/telemetry/latest")
    public List<JsonNode> latest() {
        try {
            JsonNode[] payload = streamWorkerClient.get().uri("/analytics/latest").retrieve().body(JsonNode[].class);
            return payload == null ? List.of() : List.of(payload);
        } catch (RestClientException exception) {
            return List.of();
        }
    }

    @GetMapping("/telemetry/series/{deviceId}")
    public List<JsonNode> series(@PathVariable String deviceId) {
        try {
            JsonNode[] payload = streamWorkerClient.get().uri("/analytics/series/{id}", deviceId).retrieve().body(JsonNode[].class);
            return payload == null ? List.of() : List.of(payload);
        } catch (RestClientException exception) {
            return List.of();
        }
    }

    @GetMapping("/telemetry/alerts")
    public List<JsonNode> alerts() {
        try {
            JsonNode[] payload = alertApiClient.get().uri("/alerts/recent").retrieve().body(JsonNode[].class);
            return payload == null ? List.of() : List.of(payload);
        } catch (RestClientException exception) {
            return List.of();
        }
    }
}
