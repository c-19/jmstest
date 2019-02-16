package io.c19.jmstest.rest;

import io.c19.jmstest.client.MessagingClient;
import io.c19.jmstest.common.MessageStore;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Stateless
@Path("/")
public class JmsRestService
{
    private MessagingClient messagingClient;
    private MessageStore messageStore;

    @Inject
    public JmsRestService(MessagingClient messagingClient, MessageStore messageStore )
    {
        this.messagingClient = messagingClient;
        this.messageStore = messageStore;
    }

    public JmsRestService()
    {
        //CDI no-args
    }

    @GET
    @Path( "message" )
    public Response getMessage( )
    {
        String message = messageStore.removeLast();
        return Response.ok( message ).build();
    }

    @POST
    @Path( "message" )
    public Response postMessage( String message )
    {
        messagingClient.createMessage( message );
        return Response.ok( message ).build();
    }
}
