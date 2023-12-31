package com.javaioc;

import java.util.UUID;
import com.javaioc.interfaces.IComplexObject;
import com.javaioc.interfaces.ISimpleObject;

/**
 *
 */
public class ComplexObject implements IComplexObject {

    private final UUID _id;
    private final ISimpleObject _service;

    public ComplexObject(ISimpleObject service) {
        _id = UUID.randomUUID();
        _service = service;
    }

    @Override
    public UUID getId() {
        return _id;
    }

    @Override
    public String toString() {
        return _id + "::" + _service.toString();
    }
}
