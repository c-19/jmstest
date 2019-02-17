/*
 * Copyright (c) 2018 - 2019, C19, all rights reserved.
 *
 * This software is licensed under under GPL-3.0-only or GPL-3.0-or-later, https://opensource.org/licenses/GPL-3.0
 */

package io.c19.playground.jmstest.common

import spock.lang.Specification
import spock.lang.Unroll

class MessageStoreImplTest extends Specification
{
    MessageStore instance

    def setup()
    {
        instance = new MessageStoreImpl()
    }

    def "Add null message throws NullPointerException"()
    {
        when:
        instance.push( null )

        then:
        thrown NullPointerException
    }

    def "Pop message when empty returns empty optional"()
    {
        when:
        Optional<String> actual = instance.pop()

        then:
        !actual.isPresent()
    }

    @Unroll
    def "Add message increments count and then returns in right order."()
    {
        given:
        List<String> expected = messages.reverse()
        List<String> actual = []

        when:
        messages.forEach( {m-> instance.push( m ) } )

        then:
        instance.size() == size

        when:
        while( instance.size() != 0 )
        {
            actual.add( instance.pop().orElse( "EMPTY" ) )
        }

        then:
        actual == expected

        where:
        messages                    || size
        ["Message 1", "Message 2" ] || 2
        ["M", "2", "" ]            || 3
    }

}
