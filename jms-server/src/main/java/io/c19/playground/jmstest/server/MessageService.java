/*
 * Copyright (c) 2018 - 2019, C19, all rights reserved.
 *
 * This software is licensed under under GPL-3.0-only or GPL-3.0-or-later, https://opensource.org/licenses/GPL-3.0
 */

package io.c19.playground.jmstest.server;

import io.c19.playground.jmstest.common.MessageStore;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName="jmsMyQueue", activationConfig = {
        @ActivationConfigProperty(propertyName="destination", propertyValue = "java:global/jms/myQueue" ),
        @ActivationConfigProperty(propertyName="messagingType", propertyValue="javax.jms.MessageListener"),
        @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue")
})
public class MessageService implements MessageListener
{
    private MessageStore store;

    @Inject
    public MessageService( MessageStore store )
    {
        this.store = store;
    }

    public MessageService()
    {
        //CDI - No-arg
    }

    @Override
    public void onMessage(Message message)
    {
        try
        {
            if (message instanceof TextMessage)
            {
                this.store.push(((TextMessage) message).getText());
            }
        }
        catch( JMSException e )
        {
            this.store.push( e.getMessage() );
        }
    }
}
