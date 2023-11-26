package com.javaioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.javaioc.interfaces.IServiceCollection;

import scala.collection.mutable.HashMap;

/**
 *
 */
public class ServiceCollection implements IServiceCollection {

    // TODO: These aren't thread safe

    private final HashMap<String, ServiceItem> _services;

    public ServiceCollection() {
        _services = new HashMap<>();
    }

    @Override
    public <TInterfaceType, TClassType> IServiceCollection addScoped(Class<TInterfaceType> iface,
            Class<TClassType> clazz) {

        // TODO: Not sure how to do "scoped" yet.

        _services.put(iface.getName(), new ServiceItem(clazz, ServiceLifetime.SCOPED));

        return this;
    }

    @Override
    public <TInterfaceType, TClassType> IServiceCollection addSingleton(Class<TInterfaceType> iface,
            Class<TClassType> clazz) {

        _services.put(iface.getName(), new ServiceItem(clazz, ServiceLifetime.SINGLETON));

        return this;
    }

    @Override
    public <TInterfaceType, TClassType> IServiceCollection addTransient(Class<TInterfaceType> iface,
            Class<TClassType> clazz) {

        // TODO: How to check if class implements interface?

        _services.put(iface.getName(), new ServiceItem(clazz, ServiceLifetime.TRANSIENT));

        return this;
    }

    @Override
    public <TInterfaceType> TInterfaceType getService(Class<TInterfaceType> iface) {

        return construct(iface.getName());
    }

    // ----==== PRIVATE ====---------------------------------------------------

    private <TInterfaceType> TInterfaceType construct(String name) {

        // TODO: Use a stack to detect recursion.

        ServiceItem serviceItem = _services.get(name).get();

        if (serviceItem == null) {
            return null;
        }

        Constructor<?> foundConstructor = serviceItem.Constructor;

        if (foundConstructor == null) {

            // Get all the constructors

            Constructor<?>[] constructors = serviceItem.Service.getConstructors();

            int maxArgs = -1;

            for (Constructor<?> constructor : constructors) {
                // Find the constructor with the most parameters

                if (constructor.getParameterCount() > maxArgs) {

                    foundConstructor = constructor;
                    maxArgs = constructor.getParameterCount();
                }
            }

            serviceItem.Constructor = foundConstructor;
        }

        Object instance = null;

        if (serviceItem.Lifetime == ServiceLifetime.SINGLETON && serviceItem.Singleton != null) {
            instance = serviceItem.Singleton;
        } else if (foundConstructor != null) {

            // Construct all the parameters

            int index = 0;
            Object[] arguments = new Object[foundConstructor.getParameterCount()];
            Class<?>[] types = foundConstructor.getParameterTypes();

            for (Class<?> type : types) {
                arguments[index++] = construct(type.getName());
            }

            try {

                // Construct the instance with the arguments from above

                instance = foundConstructor.newInstance(arguments);

            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException ex) {
                ex.printStackTrace();
            }

            if (serviceItem.Lifetime == ServiceLifetime.SINGLETON) {
                serviceItem.Singleton = instance;
            }
        }

        return (TInterfaceType) instance;
    }
}
