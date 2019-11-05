package com.easymall.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropUtils {
    private static Properties properties = new Properties();

    static {
        try {
            String path = PropUtils.class.getClassLoader().getResource("conf.properties").getPath();
            System.out.println(path);
            properties.load(new FileInputStream(new File(path)));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private PropUtils() {
    }

    public static String getValue(String key) {
        return properties.getProperty(key);
    }
}
