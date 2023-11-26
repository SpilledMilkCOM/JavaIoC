package com.javaioc.interfaces;

/**
 *
 */
public interface IServiceCollection {

    <TInterfaceType, TClassType> IServiceCollection addScoped(Class<TInterfaceType> iface, Class<TClassType> clazz);

    <TInterfaceType, TClassType> IServiceCollection addSingleton(Class<TInterfaceType> iface, Class<TClassType> clazz);

    <TInterfaceType, TClassType> IServiceCollection addTransient(Class<TInterfaceType> iface, Class<TClassType> clazz);

    <TInterfaceType> TInterfaceType getService(Class<TInterfaceType> iface);
}
