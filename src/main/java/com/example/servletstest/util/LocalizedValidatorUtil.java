package com.example.servletstest.util;

import com.example.servletstest.filters.CustomMessageInterpolator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;

public class LocalizedValidatorUtil {
    public static final Locale ENGLISH_LOCALE = new Locale("en", "US");
    public static final Locale UKRAINE_LOCALE = new Locale("uk", "UA");

    private static final Map<Locale, Validator> VALIDATORS = Map.of(
            ENGLISH_LOCALE, getValidator(ENGLISH_LOCALE),
            UKRAINE_LOCALE, getValidator(UKRAINE_LOCALE)
    );

    public static Validator getValidatorByLocale(Locale userLocale) {
        if(userLocale==null){
            return VALIDATORS.get(UKRAINE_LOCALE);
        }
        return VALIDATORS.get(userLocale);
    }

    public static String getLocalizationValue(String key, Locale locale) {
        ResourceBundleLocator resourceBundleLocator = new PlatformResourceBundleLocator("localization");
        try {
            return resourceBundleLocator.getResourceBundle(locale).getString(key);
        } catch (MissingResourceException e) {
            return "MISSING BUNDLE KEY";
        }
    }

    private static Validator getValidator(Locale userLocale) {
        ResourceBundleLocator resourceBundleLocator = new PlatformResourceBundleLocator("localization");
        ResourceBundleMessageInterpolator resourceBundleMessageInterpolator =
                new ResourceBundleMessageInterpolator(resourceBundleLocator);
        CustomMessageInterpolator messageInterpolator = new CustomMessageInterpolator(resourceBundleMessageInterpolator, userLocale);
        return Validation.buildDefaultValidatorFactory()
                .usingContext()
                .messageInterpolator(messageInterpolator)
                .getValidator();
    }
}
