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
    private ResourceBundle usersBundle;
    private MessageDigest hash;

    public PropertiesAuthentication(String hashAlgorithm, String usersFileResource) throws NoSuchAlgorithmException
    {
        this.usersBundle = ResourceBundle.getBundle(usersFileResource);
        this.hash = MessageDigest.getInstance(hashAlgorithm);
    }

    public boolean authenticate(String login, String password)
    {
        if (usersBundle.containsKey(login))
        {
            return validateLoginAndPassword(login, password);
        }
        return false;
    }

    private boolean validateLoginAndPassword(String login, String password)
    {
        String storedPassword = usersBundle.getString(login);

        byte[] digest = hash.digest(password.getBytes());
        char[] hex = Hex.encodeHex(digest);
        String enteredPassword = String.valueOf(hex);

        return storedPassword.equals(enteredPassword);
    }
}
