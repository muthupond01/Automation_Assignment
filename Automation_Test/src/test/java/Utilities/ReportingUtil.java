package Utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportingUtil {

    // Method to generate a formatted timestamp
    public static String getFormattedTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return now.format(formatter);
    }
}
