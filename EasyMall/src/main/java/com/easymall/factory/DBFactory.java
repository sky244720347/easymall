package com.easymall.factory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public final class DBFactory {
    private static SqlSessionFactory factory;

    static {
        try {
            InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
            factory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DBFactory() {
    }

    public static SqlSession getSqlSession(boolean autoCommit) {
        try {
            return factory.openSession(autoCommit);
        } catch (Exception e) {
            return null;
        }
    }
}