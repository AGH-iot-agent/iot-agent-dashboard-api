package io.agh.iot.dashboard.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "dashboard")
public class DownstreamProperties {

    private String deviceApiBaseUrl = "http://iot-agent-device-api:8080";
    private String streamWorkerBaseUrl = "http://iot-agent-stream-worker:8080";
    private String alertApiBaseUrl = "http://iot-agent-alert-api:8080";

    public String getDeviceApiBaseUrl() {
        return deviceApiBaseUrl;
    }

    public void setDeviceApiBaseUrl(String deviceApiBaseUrl) {
        this.deviceApiBaseUrl = deviceApiBaseUrl;
    }

    public String getStreamWorkerBaseUrl() {
        return streamWorkerBaseUrl;
    }

    public void setStreamWorkerBaseUrl(String streamWorkerBaseUrl) {
        this.streamWorkerBaseUrl = streamWorkerBaseUrl;
    }

    public String getAlertApiBaseUrl() {
        return alertApiBaseUrl;
    }

    public void setAlertApiBaseUrl(String alertApiBaseUrl) {
        this.alertApiBaseUrl = alertApiBaseUrl;
    }
}
