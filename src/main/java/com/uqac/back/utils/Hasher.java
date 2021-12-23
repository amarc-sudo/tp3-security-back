package com.uqac.back.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class Hasher {

    public static String hashPassword(String salt, String password, Integer iterations) throws NoSuchAlgorithmException {

        byte[] bSalt = Base64.getDecoder().decode(salt);


        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(bSalt);

        byte[] hashedPassword = new byte[0];
        hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        for (int i = 0; i < iterations; i++)
            hashedPassword = md.digest(hashedPassword);

        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    public static String generateSalt() {
        SecureRandom sRandom = new SecureRandom();
        byte[] salt = new byte[16];
        sRandom.nextBytes(salt);
        String sSalt = Base64.getEncoder().encodeToString(salt);
        return sSalt;
    }
}