package com.lear;

import com.lear.logging.MethodLogger;
import com.lear.messaging.Chat;
import com.lear.messaging.IChat;
import com.lear.security.IAuthentication;
import com.lear.security.PropertiesAuthentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

/**
 * Created by Дмитрий on 09.07.2017.
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringFactory
{
    public static final String HASHING_ALGORITHM = "SHA1";

    public static final String USERS_RESOURCE = "users";

    @Bean(name = "messageHashing")
    public MessageDigest getMessageHashing()
    {
        MessageDigest digest = null;
        try
        {
            digest = MessageDigest.getInstance(HASHING_ALGORITHM);
        }
        catch (NoSuchAlgorithmException algorithmException)
        {
            System.out.println(String.format("Unknown hashing algorithm %s", HASHING_ALGORITHM));
        }
        return digest;
    }

    @Bean(name = "usersRepository")
    public ResourceBundle getUsersRepository()
    {
        return ResourceBundle.getBundle(USERS_RESOURCE);
    }

    @Bean(name = "authenticator")
    public IAuthentication getAuthenticator()
    {
        return new PropertiesAuthentication(getMessageHashing(), getUsersRepository());
    }

    @Bean(name = "chat")
    public IChat getChat()
    {
        IChat chat = new Chat();
        chat.setAuthentication(getAuthenticator());
        return chat;
    }

    @Bean(name = "loggingAspect")
    public MethodLogger getLoggingAspect()
    {
        return new MethodLogger();
    }
}
