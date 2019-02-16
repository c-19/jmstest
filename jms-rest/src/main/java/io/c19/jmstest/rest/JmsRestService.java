package io.c19.jmstest.rest;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Stateless
@Path("/")
public class JmsRestService
{
    @GET
    @Path( "message" )
    public Response getMessage( )
    {
        return Response.ok().build();
    }

    @POST
    @Path( "message" )
    public Response postMessage( String message )
    {
        return Response.ok( message ).build();
    }
}
