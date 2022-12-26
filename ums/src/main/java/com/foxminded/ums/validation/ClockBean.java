package com.foxminded.ums.validation;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.ZoneId;

@Component
public class ClockBean {
    private Clock clock;

    public ClockBean() {
        this.clock =  Clock.system(ZoneId.of("Etc/UTC"));;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public Clock getClock() {
        return clock;
    }
}
