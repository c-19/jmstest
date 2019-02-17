/*
 * Copyright (c) 2018 - 2019, C19, all rights reserved.
 *
 * This software is licensed under under GPL-3.0-only or GPL-3.0-or-later, https://opensource.org/licenses/GPL-3.0
 */

package io.c19.playground.jmstest.client;

/**
 * Client for creating messages.
 */
public interface MessagingClient
{
    /**
     * Create a message with the provided text.
     * @param message text to comprise the message.
     */
    void createMessage( String message );
}
