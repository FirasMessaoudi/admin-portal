/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.data.reader;

/**
 * Generic interface for item readers
 *
 * @param <T> the type of item to be read
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public interface IItemReader<T> {

    /**
     * Reads an item from source
     *
     * @return the read item
     */
    T read();
}
