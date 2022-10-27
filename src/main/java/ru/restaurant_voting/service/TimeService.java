package ru.restaurant_voting.service;


import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.validation.ClockProvider;
import java.time.Clock;
import java.time.ZonedDateTime;


@Service
@Getter
public class TimeService implements ClockProvider {
    private Clock clock=Clock.systemDefaultZone();

    public void appointTime(ZonedDateTime zonedDateTime) {
        this.clock = Clock.fixed(zonedDateTime.toInstant(), zonedDateTime.getZone());
    }


}
