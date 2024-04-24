package com.commonts.bundel;


import org.apache.commons.lang3.LocaleUtils;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BundleManager {
    private static Logger logger = Logger.getLogger(BundleManager.class);
    private static final String KEY_WRAPPER_BEGIN = "$translateKey{";
    private static final String KEY_WRAPPER_END = "}";
    private static final String PARAM_WRAPPER_BEGIN = "$translateParams{";
    private static final String PARAM_WRAPPER_END = "}";
    private static final String KEY_PATTERN = "\\$translateKey\\{(.*?),\\$translateParams\\{(.*?)\\}\\}";
    private static final String PARAM_PATTERN = "\\$translateParams\\{(.*?)\\}";
    public static Locale server_locale = LocaleUtils.toLocale("en");
    // The loaded message cache...
    private Map<Locale, ResourceBundle> mResourceCache = null;

    // Private instance variable.
    private static BundleManager mManager = null;

    // default private constructor.
    private BundleManager() {
        mResourceCache = new HashMap<>();
    }

    // Get the locale-specific bundle from cache.
// First load to the cache if not already loaded
    private ResourceBundle getBundle(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        if (!mResourceCache.containsKey(locale)) {
            String mMessageBundle = "bundle/HamisheBahar";
            ResourceBundle bundle = ResourceBundle.getBundle(mMessageBundle, locale);
            mResourceCache.put(locale, bundle);
        }
        return mResourceCache.get(locale);
    }

    // Thread safe Singleton pattern implementation...
    private static BundleManager getInstance() {
        synchronized (BundleManager.class) {
            if (mManager == null) {
                mManager = new BundleManager();
            }
        }
        return mManager;
    }

    // Get the message for the key using default locale.
    public static String getMessage(String key) {
        return getMessage(key, null);
    }

    // Get the message for the key and specified locale.
    public static String getMessage(String key, Locale locale) {
        try {
            return getInstance().getBundle(locale).getString(key);
        } catch (Exception e) {
            logger.error("Resource key : " + key + " not found because : " + e);
            return key;
        }
    }

    public static String translateText(String content, Locale locale) {
        if (content != null && content.length() > 0) {
            String result = content;
            List<String> wrappedKeys = unWrapKeysInText(content);
            for (String wrappedKey : wrappedKeys) {
                String[] params = getParam(wrappedKey);
                String key = unWrapKey(wrappedKey);
                String message = getMessage(key, locale);
                if (params != null && params.length > 0)
                    message = String.format(message, (Object[]) params);
                try {
                    result = result.replaceAll(makeKeyWithPattern(key, params), message);
                } catch (Exception e) {
                    logger.error(String.format("Cannot translate text : %s", content));
                    return content;
                }
            }
            return result;
        }
        return "";
    }

    private static String prepareKey(String wrappedKey) {
        return wrappedKey
                .replaceAll("$", "\\\\$")
                .replaceAll("\\{", "\\\\{")
                .replaceAll("\\}", "\\\\}");
    }

    private static String makeKeyWithPattern(String key, String[] params) {
        return String.format("\\$translateKey\\{%s,\\$translateParams\\{%s\\}\\}",
                key, params != null ? StringUtils.join(params, ":") : "");
    }

    public static String wrapKey(String key, String... values) {
        StringBuilder stringBuilder = new StringBuilder(key);
        stringBuilder.insert(0, KEY_WRAPPER_BEGIN);
        stringBuilder.insert(stringBuilder.toString().length(), ",");
        stringBuilder.insert(stringBuilder.toString().length(), PARAM_WRAPPER_BEGIN);
        stringBuilder.insert(stringBuilder.toString().length(), values != null ? StringUtils.join(values, ":") : "");
        stringBuilder.insert(stringBuilder.toString().length(), PARAM_WRAPPER_END);
        stringBuilder.insert(stringBuilder.toString().length(), KEY_WRAPPER_END);
        return stringBuilder.toString();
    }

    public static String manualTranslateText(String localeHeader, String message) {
        Locale locale = LocaleUtils.toLocale("en");
        if (localeHeader != null) {
            if (localeHeader.equals("en-US,en;q=0.5")) {
                locale = LocaleUtils.toLocale("en");
            } else {
                locale = LocaleUtils.toLocale(localeHeader.trim());
            }
        }
        return BundleManager.translateText(message, locale);
    }

    private static String unWrapKey(String key) {
        Pattern p = Pattern.compile(KEY_PATTERN);
        Matcher m = p.matcher(key);
        if (m.find())
            return m.group(1);
        return null;
    }

    private static String[] getParam(String wrappedKey) {
        Pattern p = Pattern.compile(PARAM_PATTERN);
        Matcher m = p.matcher(wrappedKey);
        if (m.find()) {
            String params = m.group(1);
            return params.split(":");
        }
        return null;
    }

    private static List<String> unWrapKeysInText(String content) {
        List<String> allMatches = new ArrayList<>();
        Pattern p = Pattern.compile(KEY_PATTERN);
        Matcher m = p.matcher(content);
        while (m.find()) {
            //allMatches.add(m.group(1));
            allMatches.add(m.group());
        }
        return allMatches;
    }

    public static String translateServerMessage(String key, String... params) {
        return translateText(wrapKey(key, params), server_locale);
    }

    public static String translateText(String key, Locale locale, String... params) {
        return translateText(wrapKey(key, params), locale);
    }

    public static String translateWrappedServerMessages(String key) {
        return translateText(key, server_locale);
    }


}
