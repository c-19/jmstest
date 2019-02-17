/*
 * Copyright (c) 2018 - 2019, C19, all rights reserved.
 *
 * This software is licensed under under GPL-3.0-only or GPL-3.0-or-later, https://opensource.org/licenses/GPL-3.0
 */

package io.c19.playground.jmstest.common;

import java.util.Optional;

/**
 * LIFO Message store.
 */
public interface MessageStore
{
    /**
     * Emplace a message into the store.
     *
     * @param message to be emplaced.
     */
    void push(String message );

    /**
     * Remove the most recent message from the store.
     *
     * @return most recent message.
     */
    Optional<String> pop();

    /**
     * Current number of messages contained within the store.
     *
     * @return number of messages.
     */
    int size();
}
