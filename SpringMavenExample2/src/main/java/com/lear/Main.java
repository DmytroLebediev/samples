package com.lear;

import com.lear.messaging.IChat;
import com.lear.security.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringFactory.class);

        IChat chat = context.getBean(IChat.class);
        chat.setMessageStream(System.out);

        User user = new User("user", "aA12345");

        if (chat.canStartMessaging(user))
        {
            chat.sendMessage("Ping!");
        }
    }
}
