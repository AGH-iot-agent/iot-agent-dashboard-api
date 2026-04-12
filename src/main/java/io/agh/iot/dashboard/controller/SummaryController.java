package io.agh.iot.dashboard.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.databind.JsonNode;

import io.agh.iot.dashboard.model.Summary;

@RestController
public class SummaryController {

    private final RestClient deviceApiClient;
    private final RestClient streamWorkerClient;
    private final RestClient alertApiClient;

    public SummaryController(@Qualifier("deviceApiClient") RestClient deviceApiClient,
                             @Qualifier("streamWorkerClient") RestClient streamWorkerClient,
                             @Qualifier("alertApiClient") RestClient alertApiClient) {
        this.deviceApiClient = deviceApiClient;
        this.streamWorkerClient = streamWorkerClient;
        this.alertApiClient = alertApiClient;
    }

        @GetMapping("/api/ping")
        public String ping() {
            return "pong";
        }

    @GetMapping("/api/summary")
    public Summary getSummary() {
        int devicesOnline = countOnlineDevices();
        int activeAlerts = countActiveAlerts();
        int eventsPerMinute = getEventsPerMinute();
        return new Summary(devicesOnline, activeAlerts, eventsPerMinute);
    }

    private int countOnlineDevices() {
        try {
            JsonNode[] devices = deviceApiClient.get().uri("/devices").retrieve().body(JsonNode[].class);
            if (devices == null) {
                return 0;
            }
            return (int) Arrays.stream(devices)
                .filter(device -> "ONLINE".equalsIgnoreCase(device.path("status").asText()))
                .count();
        } catch (RestClientException exception) {
            return 0;
        }
    }

    private int countActiveAlerts() {
        try {
            JsonNode[] alerts = alertApiClient.get().uri("/alerts/active").retrieve().body(JsonNode[].class);
            return alerts == null ? 0 : alerts.length;
        } catch (RestClientException exception) {
            return 0;
        }
    }

    private int getEventsPerMinute() {
        try {
            JsonNode payload = streamWorkerClient.get().uri("/analytics/events-per-minute").retrieve().body(JsonNode.class);
            if (payload == null || payload.path("eventsPerMinute").isMissingNode()) {
                return 0;
            }
            return payload.path("eventsPerMinute").asInt(0);
        } catch (RuntimeException exception) {
            return 0;
        }
    }
}
