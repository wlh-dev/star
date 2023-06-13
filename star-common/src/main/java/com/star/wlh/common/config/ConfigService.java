/*
package com.star.wlh.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

*/
/**
 * 提供统一的配置服务。
 *//*

@Component
public class ConfigService {

    private static Logger logger = LoggerFactory.getLogger(ConfigService.class);

    private ConfigService() {}

    */
/**
     * 当前是否处于开发模式。
     *
     * @return 如果是返回 true，否则返回 false
     *//*

    public static boolean isDevMode() {
        return Boolean.getBoolean("pacific.mode.dev");
    }

    */
/**
     * 返回指定Key所对应的字符串值。
     *
     * @param key 配置KEY
     * @return 配置值，如果不存在则返回null
     *//*

    public static String getString(String key) {
        return getString(key, null);
    }

    */
/**
     * 返回指定Key所对应的字符串值。<br>
     * 如果对应的值不存在，则返回默认值，并将默认值作为key的配置值存储起来。
     *
     * @param key 配置KEY
     * @param defaultVal 默认值
     * @return 配置值，如果不存在则返回默认值
     *//*

    public static String getString(String key, String defaultVal) {
        return getValue(String.class,  key, defaultVal);
    }

    */
/**
     * 返回指定Key所对应的int类型的值。
     *
     * @param key 配置KEY
     * @return 配置值，如果不存在则返回0
     *//*

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    */
/**
     * 返回指定Key所对应的int类型的值。<br>
     * 如果对应的值不存在，则返回默认值，并将默认值作为key的配置值存储起来。
     *
     * @param key 配置KEY
     * @param defaultVal 默认值
     * @return 配置值，如果不存在则返回默认值
     *//*

    public static int getInt(String key, int defaultVal) {
        return getValue(Integer.class, key, defaultVal);
    }

    */
/**
     * 返回指定Key所对应的数值类型的值。
     *
     * @param key 配置KEY
     * @return 配置值，如果不存在则返回0
     *//*

    public static long getLong(String key) {
        return getLong(key, 0);
    }

    */
/**
     * 返回指定Key所对应的数值类型的值。<br>
     * 如果对应的值不存在，则返回默认值，并将默认值作为key的配置值存储起来。
     *
     * @param key 配置KEY
     * @param defaultVal 默认值
     * @return 配置值，如果不存在则返回默认值
     *//*

    public static long getLong(String key, long defaultVal) {
        return getValue(Long.class, key, defaultVal);
    }

    */
/**
     * 返回指定Key所对应的double类型的值。
     *
     * @param key 配置KEY
     * @return 配置值，如果不存在则返回0
     *//*

    public static double getDouble(String key) {
        return getDouble(key, 0);
    }

    */
/**
     * 返回指定Key所对应的double类型的值。<br>
     * 如果对应的值不存在，则返回默认值，并将默认值作为key的配置值存储起来。
     *
     * @param key 配置KEY
     * @param defaultVal 默认值
     * @return 配置值，如果不存在则返回默认值
     *//*

    public static double getDouble(String key, double defaultVal) {
        return getValue(Double.class, key, defaultVal);
    }

    */
/**
     * 返回指定Key所对应的double类型的值。
     *
     * @param key 配置KEY
     * @return 配置值，如果不存在则返回false
     *//*

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    */
/**
     * 返回指定Key所对应的boolean类型的值。<br>
     * 如果对应的值不存在，则返回默认值，并将默认值作为key的配置值存储起来。
     *
     * @param key 配置KEY
     * @param defaultVal 默认值
     * @return 配置值，如果不存在则返回默认值
     *//*

    public static boolean getBoolean(String key, boolean defaultVal) {
        return getValue(Boolean.class, key, defaultVal);
    }

    */
/**
     * 返回指定Key所对应的字符串数组形式的值。
     *
     * @param key 配置KEY
     * @return 配置值，如果不存在或不是字符串数组，则返回null
     *//*

    public static String[] getStrings(String key) {
        return getStrings(key, null);
    }

    */
/**
     * 返回指定Key所对应的字符串数组形式的值。<br>
     * 如果对应的值不存在，则返回默认值，并将默认值作为key的配置值存储起来。
     *
     * @param key 配置KEY
     * @param defaultVals 默认值
     * @return 配置值，如果不存在则返回默认值
     *//*

    public static String[] getStrings(String key, String[] defaultVals) {
        return getValue(String[].class, key, defaultVals);
    }

    */
/**
     * 返回指定Key所对应的字典形式的值。
     *
     * @param key 配置KEY
     * @return 配置值，如果不存在则返回null
     *//*

    public static Map<String, String> getMap(String key) {
        return getMap(key, null);
    }

    */
/**
     * 返回指定Key所对应的字典形式的值。<br>
     * 如果对应的值不存在，则返回默认值，并将默认值作为key的配置值存储起来。
     *
     * @param key 配置KEY
     * @param defaultVals 默认值
     * @return 配置值，如果不存在则返回默认值
     *//*

    @SuppressWarnings("unchecked")
    public static Map<String, String> getMap(String key, Map<String, String> defaultVals) {
        return getValue(Map.class, key, defaultVals);
    }

    */
/*
     * 返回指定类型的资源的值，如果不存在则返回默认值。
     *
     * @param valueClazz 值的类型
     * @param key 资源的KEY
     * @param defaultVal 默认值
     * @return 返回值
     *//*

    private static <T> T getValue(Class<T> valueClazz, String key, T defaultVal) {
        Object value = null;

        // 原始类型
        if (valueClazz == String.class || ClassUtils.isPrimitiveOrWrapper(valueClazz)) {
            value = System.getProperty(key); // 优先从系统参数获取
        }

        if (value == null) {
            // value = ConfigServices.isConfigServiceEnabled() ? ConfigServices.getConfigValue(valueClazz, key) : getLocalCacheSync().get(key); // 从配置服务获取
        }
        if (value == null) {
            return defaultVal;
        }
        if (value.getClass() == valueClazz || valueClazz.isAssignableFrom(value.getClass())) {
            return valueClazz.cast(value);
        } else {
            return valueClazz.cast(ConfigServices.convertValue(valueClazz, value.toString()));
        }
    }

    //----------------- 本地缓存处理（主要用于配置服务被禁用的场合） ----------------------//
    private static volatile Map<String, Object> localCache;  // NOSONAR 这里利用了经典的 double-check 来实现单例

    public static void reloadLocalCache() {
        localCache = null;
        getLocalCacheSync();
    }

    static Map<String, Object> getLocalCacheSync() {
        if (localCache == null) {
            synchronized (ConfigService.class) {
                if (localCache == null) {
                    initLocalCache();
                }
            }
        }
        return localCache;
    }

    static void initLocalCache() {
        localCache = new ConcurrentHashMap<>();
        if (ConfigServices.isConfigServiceEnabled()) {
            return;
        }

        // 如果禁用配置服务 ，从本地配置文件加载配置信息
        Set<String> propFiles = ConfigFileLocator.getLocalConfFiles();
        if (propFiles == null || propFiles.isEmpty()) {
            logger.warn("no property file found");
            return;
        }
        logger.debug("found property files: {}", propFiles);

        Properties props = new Properties();
        try {
            for (String propFile : propFiles) {
                if (propFile.startsWith("file://")) {
                    propFile = propFile.substring(7);
                }
                readPropFile(props, propFile);
                initCacheWithProps(props);
            }

            // 覆盖系统参数
            Properties sysprops = System.getProperties();
            for (String key : sysprops.stringPropertyNames()) {
                if (localCache.containsKey(key)) {
                    localCache.put(key, sysprops.getProperty(key));
                }
            }

        } catch (IOException e) {
            logger.error("failed to load property file", e);
        }
    }

    */
/*
     * 读取配置文件。
     *//*

    private static void readPropFile(Properties props, String propFile) throws IOException {
        try (Reader fileReader = new FileReader(propFile)) {
            props.clear();
            props.load(fileReader);
        }
    }

    private static void initCacheWithProps(Properties props) {
        if (props == null) {
            return;
        }
        props.entrySet().stream().forEach((Map.Entry<Object,Object> entry) ->
                localCache.put((String) entry.getKey(), entry.getValue())
        );
    }
}
*/
