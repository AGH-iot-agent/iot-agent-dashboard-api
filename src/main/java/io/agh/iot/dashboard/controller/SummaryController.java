package io.agh.iot.dashboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SummaryController {

    @GetMapping("/summary")
    public Summary getSummary() {
        return new Summary(3, 1, 42);
    }

    record Summary(int devicesOnline, int activeAlerts, int eventsPerMinute) {}
}
