/*
 * Copyright (c) 2018 - 2019, C19, all rights reserved.
 *
 * This software is licensed under under GPL-3.0-only or GPL-3.0-or-later, https://opensource.org/licenses/GPL-3.0
 */

package io.c19.playground.jmstest.common;

import javax.ejb.Local;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
@Local(MessageStore.class)
public class MessageStoreImpl implements MessageStore
{
    private List<String> messages = new ArrayList<>();

    @Override
    public void push(String message )
    {
        Objects.requireNonNull( message, "Message is null." );
        this.messages.add( message );
    }

    @Override
    public Optional<String> pop()
    {
        if( this.messages.isEmpty() )
        {
            return Optional.empty();
        }
        return Optional.of( this.messages.remove( this.messages.size() -1 ) );
    }

    @Override
    public int size()
    {
        return this.messages.size();
    }

}
