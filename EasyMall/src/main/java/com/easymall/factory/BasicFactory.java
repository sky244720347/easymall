package com.easymall.factory;

import com.easymall.exception.MsgException;
import com.easymall.service.Service;
import com.easymall.utils.PropUtils;
import com.easymall.utils.TranManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BasicFactory {
    private static final BasicFactory factory = new BasicFactory();

    private BasicFactory() {
    }

    public static BasicFactory getFactory() {
        return factory;
    }

    public <T> T getInstance(Class<T> clazz) {
        try {
            String name = PropUtils.getValue(clazz.getSimpleName());
            Class<?> c = Class.forName(name);
            if (Service.class.isAssignableFrom(c)) {
                final T t = (T) c.newInstance();
                InvocationHandler serviceHandler = new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result = null;
                        try {
                            result = method.invoke(t, args);
                            TranManager.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                            TranManager.rollback();
                            throw new MsgException(e.getCause().getMessage());
                        } finally {
                            TranManager.close();
                        }
                        return result;
                    }
                };
                return (T) Proxy.newProxyInstance(c.getClassLoader(), c.getInterfaces(), serviceHandler);
            } else {
                System.err.println("Not supported class: " + clazz);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}