package com.lear.security;

/**
 * Created by Дмитрий on 09.07.2017.
 */
public class User
{
    private String login;
    private String password;

    public User()
    { }

    public User(String login, String password)
    {
        this.login = login;
        this.password = password;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword()
    {
        this.password = password;
    }
}
