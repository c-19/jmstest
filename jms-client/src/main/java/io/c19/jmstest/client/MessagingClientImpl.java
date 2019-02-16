package io.c19.jmstest.client;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@Stateless
@Local(MessagingClient.class)
public class MessagingClientImpl implements MessagingClient
{
    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName="java:global/jms/myQueue")
    private Queue queue;

    public void createMessage( String message )
    {
        try
        {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession();
            MessageProducer messageProducer = session.createProducer(queue);

            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(message);
            messageProducer.send(textMessage);
        }
        catch( JMSException e )
        {
            e.printStackTrace();
        }
    }


}
