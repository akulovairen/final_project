package com.example.servletstest.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Helper class with common logic related to Database
 */
public class RequestUtils {
    private RequestUtils() {
    }

    public static void restoreFormValues(HttpServletRequest req) {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Map<String, String> restoredValues =
                parameterMap.entrySet()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, stringEntry -> stringEntry.getValue()[0]));
        req.getSession().setAttribute("restoredValues", restoredValues);
    }
}
