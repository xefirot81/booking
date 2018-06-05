package utils;

import lombok.Getter;

@Getter
public enum MonthNumber {
    CURRENT(0),
    NEXT(1);

    private final Integer numberOfMonth;

    MonthNumber(Integer numberOfMonth) {
        this.numberOfMonth = numberOfMonth;
    }
}
