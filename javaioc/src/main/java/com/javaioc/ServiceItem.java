package com.javaioc;

import java.lang.reflect.Constructor;

/**
 * A data object
 */
public class ServiceItem {

    public ServiceItem(Class<?> clazz, ServiceLifetime lifetime) {
        Lifetime = lifetime;
        Service = clazz;
        Singleton = null;
    }

    public Constructor<?> Constructor;

    public ServiceLifetime Lifetime;

    public Class<?> Service;

    public Object Singleton;
}
