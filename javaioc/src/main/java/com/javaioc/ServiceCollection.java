package com.javaioc;

import java.util.concurrent.Callable;

import com.javaioc.interfaces.IServiceCollection;

import scala.collection.mutable.HashMap;

/**
 *
 */
public class ServiceCollection implements IServiceCollection {

    private final HashMap<String, Callable<Object>> _services;

    public ServiceCollection() {
        _services = new HashMap<String, Callable<Object>>();
    }

    @Override
    public <TInterfaceType, TClassType>  IServiceCollection addScoped(Class<TInterfaceType> iface, Class<TClassType> clazz) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addScoped'");
    }

    @Override
    public <TInterfaceType, TClassType> IServiceCollection addSingleton(Class<TInterfaceType> iface, Class<TClassType> clazz) {

        // Build of list of parameters for constructor of clazz and construct them.
        // Then construct clazz.

        throw new UnsupportedOperationException("Unimplemented method 'addSingleton'");
    }

    @Override
    public <TInterfaceType, TClassType> IServiceCollection addTransient(Class<TInterfaceType> iface, Class<TClassType> clazz) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTransient'");
    }

    @Override
    public Object getService(Class<?> iface) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getService'");
    }
}
