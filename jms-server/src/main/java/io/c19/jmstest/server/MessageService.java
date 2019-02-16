package io.c19.jmstest.server;

import io.c19.jmstest.common.MessageStore;

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
                this.store.add(((TextMessage) message).getText());
            }
        }
        catch( JMSException e )
        {
            this.store.add( e.getMessage() );
        }
    }
}
