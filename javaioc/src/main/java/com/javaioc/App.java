package com.javaioc;

import com.javaioc.interfaces.IComplexObject;
import com.javaioc.interfaces.IServiceCollection;
import com.javaioc.interfaces.ISimpleObject;

/**
 * The main application.
 */
public final class App {

    private App() {
    }

    /**
     * Command line to test ServiceCollection.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");

        IServiceCollection iocContainer = new ServiceCollection();

        iocContainer.addSingleton(IComplexObject.class, ComplexObject.class);
        iocContainer.addSingleton(ISimpleObject.class, SimpleObject.class);
    }
}
