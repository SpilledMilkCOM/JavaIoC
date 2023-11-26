package com.javaioc;

import com.javaioc.interfaces.IComplexObject;
import com.javaioc.interfaces.ISimpleObject;

/**
 *
 */
public class ComplexObject implements IComplexObject {

    private final ISimpleObject _service;

    public ComplexObject(ISimpleObject service) {
        _service = service;
    }
}
