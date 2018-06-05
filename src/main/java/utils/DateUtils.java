package utils;


import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class DateUtils {

    public Instant getDateTime() {

        return new Date().toInstant();
    }


}