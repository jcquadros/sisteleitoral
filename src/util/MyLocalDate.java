package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyLocalDate {
    private LocalDate date;

    public MyLocalDate(String date) {
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public LocalDate getDate() {
        return date;
    }
}