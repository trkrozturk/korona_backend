package com.ante.grup.korona.constants;

import java.util.regex.Pattern;

public class ParsePatterns {
    public static Pattern DATE_PATTERN = Pattern.compile(
            "[0-3]?[0-9].[0-1]?[0-9].(?:[0-9]{2})?[0-9]{2}");
    public static Pattern DIGIT_PATTERN = Pattern.compile(
            "[0-9]+");
}
