package com.javaioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.javaioc.interfaces.IComplexObject;
import com.javaioc.interfaces.IServiceCollection;
import com.javaioc.interfaces.ISimpleObject;

public class ServiceCollectionTests {

    @Test
    void ServiceCollection_Construct_GetsTransientObject() {
        IServiceCollection test = constructTestObject();

        Assertions.assertNotNull(test);
    }

    @Test
    void ServiceCollection_AddTransient_AddingDuplicateReplacesEntry() {
        IServiceCollection test = constructTestObject();

        test.addTransient(ISimpleObject.class, SimpleObject.class);
        test.addTransient(ISimpleObject.class, SimpleObject2.class);

        ISimpleObject actual = test.getService(ISimpleObject.class);

        Assertions.assertNotNull(actual);
        Assertions.assertTrue(actual instanceof SimpleObject2);
    }

    @Test
    void ServiceCollection_GetService_GetsSingletonComplexObject() {
        IServiceCollection test = constructTestObject();

        test.addSingleton(ISimpleObject.class, SimpleObject.class);

        ISimpleObject actual = test.getService(ISimpleObject.class);

        Assertions.assertNotNull(actual);
        Assertions.assertTrue(actual instanceof SimpleObject);

        ISimpleObject actual2 = test.getService(ISimpleObject.class);

        Assertions.assertSame(actual, actual2);
    }

    @Test
    void ServiceCollection_GetService_GetsSingletonSimpleObject() {
        IServiceCollection test = constructTestObject();

        test.addSingleton(ISimpleObject.class, SimpleObject.class);

        ISimpleObject actual = test.getService(ISimpleObject.class);

        Assertions.assertNotNull(actual);
        Assertions.assertTrue(actual instanceof SimpleObject);

        ISimpleObject actual2 = test.getService(ISimpleObject.class);

        Assertions.assertSame(actual, actual2);
    }

    @Test
    void ServiceCollection_GetService_GetsTransientComplexObject() {
        IServiceCollection test = constructTestObject();

        test.addTransient(IComplexObject.class, ComplexObject.class);
        test.addTransient(ISimpleObject.class, SimpleObject.class);

        IComplexObject actual = test.getService(IComplexObject.class);

        Assertions.assertNotNull(actual);
        Assertions.assertTrue(actual instanceof ComplexObject);
    }

    @Test
    void ServiceCollection_GetService_GetsTransientSimpleObject() {
        IServiceCollection test = constructTestObject();

        test.addTransient(ISimpleObject.class, SimpleObject.class);

        ISimpleObject actual = test.getService(ISimpleObject.class);

        Assertions.assertNotNull(actual);
        Assertions.assertTrue(actual instanceof SimpleObject);

        ISimpleObject actual2 = test.getService(ISimpleObject.class);

        Assertions.assertNotSame(actual, actual2);
    }

    // ----==== PRIVATE ====---------------------------------------------------

    private IServiceCollection constructTestObject() {
        return new ServiceCollection();
    }
}
