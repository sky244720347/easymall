package com.easymall.utils;

import com.easymall.factory.DBFactory;
import org.apache.ibatis.session.SqlSession;

public class TranManager {
    private static final ThreadLocal<Boolean> tlAutoCommit = new ThreadLocal<>();
    private static final ThreadLocal<SqlSession> tlSession = new ThreadLocal<SqlSession>();

    private TranManager() {
    }

    public static SqlSession getConn() {
        return getConn(true);
    }

    public static SqlSession getTranConn() {
        return getConn(false);
    }

    private static SqlSession getConn(boolean autoCommit) {
        SqlSession session = tlSession.get();
        if (session != null && autoCommit != tlAutoCommit.get()) {
            session.close();
            tlSession.remove();
            session = null;
        }
        if (session == null) {
            tlAutoCommit.set(autoCommit);
            session = DBFactory.getSqlSession(autoCommit);
            tlSession.set(session);
        }
        return session;
    }

    public static void rollback() {
        if (tlAutoCommit.get()) {
            return;
        }
        try {
            tlSession.get().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void commit() {
        if (tlAutoCommit.get()) {
            return;
        }
        try {
            tlSession.get().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        SqlSession session = tlSession.get();
        if (session != null) {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            tlSession.remove();
        }
    }
}