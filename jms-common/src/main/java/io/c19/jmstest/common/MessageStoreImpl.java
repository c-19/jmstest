package io.c19.jmstest.common;

import javax.ejb.Local;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Local(MessageStore.class)
public class MessageStoreImpl implements MessageStore
{
    private List<String> messages = new ArrayList<>();

    @Override
    public void add( String message )
    {
        this.messages.add( message );
    }

    @Override
    public String removeLast()
    {
        if( this.messages.isEmpty() )
        {
            return "EMPTY";
        }
        return this.messages.remove( this.messages.size() -1 );
    }

}
