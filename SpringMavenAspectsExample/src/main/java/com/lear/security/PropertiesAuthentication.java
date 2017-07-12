package com.lear.security;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

/**
 * Created by Дмитрий on 25.06.2017.
 */
public class PropertiesAuthentication implements IAuthentication
{
    private ResourceBundle usersRepository;
    private MessageDigest hash;

    public PropertiesAuthentication(MessageDigest hash, ResourceBundle usersRepository)
    {
        this.usersRepository = usersRepository;
        this.hash = hash;
    }

    public boolean authenticate(String login, String password)
    {
        if (usersRepository.containsKey(login))
        {
            return validateLoginAndPassword(login, password);
        }
        return false;
    }

    private boolean validateLoginAndPassword(String login, String password)
    {
        String storedPassword = usersRepository.getString(login);

        byte[] digest = hash.digest(password.getBytes());
        char[] hex = Hex.encodeHex(digest);
        String enteredPassword = String.valueOf(hex);

        return storedPassword.equals(enteredPassword);
    }
}
