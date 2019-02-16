package io.c19.jmstest.common;

public interface MessageStore
{
    void add( String message );
    String removeLast();
}
