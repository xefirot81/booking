package utils;

import com.google.common.base.CaseFormat;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;

@Component
public class TextUtils {

    public String transformToCamelCase(String st) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, st);
    }

    public String transformToLowerCamelCase(String st) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, st);
    }

    private String getRandomString(int length){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder string = new StringBuilder();
        Random rnd = new Random();
        while (string.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * chars.length());
            string.append(chars.charAt(index));
        }
        return string.toString().toLowerCase();
    }

    public String generateRandomEmail(Instant instant) {

        return getRandomString(8).concat(String.valueOf(instant.toEpochMilli())).concat("test@qa.com");
    }
}
