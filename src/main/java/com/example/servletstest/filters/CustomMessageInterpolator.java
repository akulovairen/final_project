package com.example.servletstest.filters;

import javax.validation.MessageInterpolator;
import java.util.Locale;

public class CustomMessageInterpolator implements MessageInterpolator {
    private final MessageInterpolator interpolator;
    private Locale currentLocale;

    public CustomMessageInterpolator(MessageInterpolator messageInterpolator, Locale locale) {
        this.interpolator = messageInterpolator;
        this.currentLocale = locale;
    }

    @Override
    public String interpolate(String messageTemplate, Context context) {
        return interpolator.interpolate(messageTemplate, context, currentLocale);
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        return interpolator.interpolate(messageTemplate, context, locale);
    }
}
