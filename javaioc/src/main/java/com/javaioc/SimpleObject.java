package com.javaioc;

import java.util.UUID;

import com.javaioc.interfaces.ISimpleObject;

/**
 *
 */
public class SimpleObject implements ISimpleObject {

    private final UUID _id;

    public SimpleObject() {
        _id = UUID.randomUUID();
    }

    @Override
    public UUID getId() {
        return _id;
    }

    @Override
    public String toString() {
       return _id.toString();
    }
}
