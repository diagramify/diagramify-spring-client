package com.diagramify.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenUtils {

    public static String extractToken(String input) {

        final String diagramify_TOKEN_EXTRACT_PATTERN = "::<(.*?)>::";

        Matcher matcher = Pattern.compile(diagramify_TOKEN_EXTRACT_PATTERN).matcher(input);

        return matcher.find() ? matcher.group(1).trim() : null;
    }

}
