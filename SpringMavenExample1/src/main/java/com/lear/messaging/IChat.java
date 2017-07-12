package com.lear.messaging;

import com.lear.security.IAuthentication;
import com.lear.security.User;

import java.io.PrintStream;

/**
 * Created by Дмитрий on 25.06.2017.
 */
public interface IChat
{
    void setAuthentication(IAuthentication authentication);

    void setMessageStream(PrintStream messageStream);

    boolean canStartMessaging(User user);

    void sendMessage(String message);
}
