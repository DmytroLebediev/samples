package com.lear.messaging;

import com.lear.security.IAuthentication;
import com.lear.security.User;

import java.io.PrintStream;

/**
 * Created by Дмитрий on 25.06.2017.
 */
public class Chat implements IChat
{
    private IAuthentication authentication;
    private PrintStream messageStream;

    public void setAuthentication(IAuthentication authentication)
    {
        this.authentication = authentication;
    }

    public void setMessageStream(PrintStream messageStream)
    {
        this.messageStream = messageStream;
    }

    public boolean canStartMessaging(User user)
    {
        return  authentication.authenticate(user.getLogin(), user.getPassword());
    }

    public void sendMessage(String message)
    {
        messageStream.println(message);
    }
}
