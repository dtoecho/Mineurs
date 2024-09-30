package com.newlecmineursprj.util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public abstract class SearchModuleUtil {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static List<String> sellStatusList() {
        return Arrays.asList("전체","판매함","판매안함");
    }
    public static Integer searchBySellStatus(String selectedSellStatus) {
        return switch (selectedSellStatus) {
            case "판매함" -> 1;
            case "판매안함" -> 0;
            default -> null;
        };
    }
    public static List<String> DisplayStatusList() {
        return Arrays.asList("전체", "진열함", "진열안함");
    }

    public static Integer searchByDisplayStatus(String selectedDisplayStatus) {
        return switch (selectedDisplayStatus) {
            case "진열함" -> 1;
            case "진열안함" -> 0;
            default -> null;
        };
    }

    public static String getStartDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
