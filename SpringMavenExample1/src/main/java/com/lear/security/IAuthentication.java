package com.lear.security;

/**
 * Created by Дмитрий on 25.06.2017.
 */
public interface IAuthentication
{
    boolean authenticate(String login, String password);
}
