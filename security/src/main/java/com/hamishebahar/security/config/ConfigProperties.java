package com.hamishebahar.security.config;

import com.hamishebahar.security.commonts.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * ================================================================
 * Project Name: hamishebahar
 * Class: Configuration
 * Created by: Asus on 3/19/2025
 * Description: This class does ...
 * ================================================================
 */

@RequiredArgsConstructor
@Configuration
public class ConfigProperties {
    private final Environment environment;

    public String getArgumentValue(String defaultMessage) {
        return environment.getProperty(defaultMessage);
    }

    public String getApplicationName() {
        return environment.getRequiredProperty("spring.application.name");
    }

    public String getArgumentValueWithBundle(String propertiesFileName, String text, Object... args) {
        String message = null;
        try {
            if (StringUtils.hasText(text)) {
                ResourceBundle bundle = ResourceBundle.getBundle(propertiesFileName);
                message = bundle.getString(text);
                if (StringUtils.hasText(message)) {
                    if (args != null && args.length > 0) {
                        message = String.format(message, args);
                    }
                }
            }
            return message;
        } catch (MissingResourceException e) {
            return text;
        }
    }
}
