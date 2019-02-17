/*
 * Copyright (c) 2018 - 2019, C19, all rights reserved.
 *
 * This software is licensed under under GPL-3.0-only or GPL-3.0-or-later, https://opensource.org/licenses/GPL-3.0
 */

package io.c19.playground.jmstest.server

import io.c19.playground.jmstest.common.MessageStore
import io.c19.playground.jmstest.common.MessageStoreImpl
import spock.lang.Specification

import javax.jms.JMSException
import javax.jms.Message
import javax.jms.TextMessage

class MessageServiceTest extends Specification
{

    MessageStore messageStore = new MessageStoreImpl()
    MessageService instance
    TextMessage messageMock

    def setup()
    {
        instance = new MessageService( messageStore )
        messageMock = Mock()
    }

    def "Add TextMessage is stored in the message store."()
    {
        given:
        String message = "Message to be sent"
        1 * messageMock.getText() >> message

        when:
        instance.onMessage( messageMock )

        then:
        messageStore.size() == 1
        messageStore.pop().get() == message
    }

    def "Add non TextMessage nothing is stored."()
    {
        given:
        Message message = Mock()

        when:
        instance.onMessage( message )

        then:
        noExceptionThrown()
        messageStore.size() == 0
    }

    def "On message throws exception, exception message is stored"()
    {
        given:
        String exceptionMessage = "This is the exception message"
        1 * messageMock.getText() >> {
            throw new JMSException( exceptionMessage )
        }

        when:
        instance.onMessage( messageMock )

        then:
        noExceptionThrown()
        messageStore.size() == 1
        messageStore.pop().get() == exceptionMessage
    }

}
