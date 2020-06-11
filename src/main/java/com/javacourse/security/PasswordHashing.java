package com.javacourse.security;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;


import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;

public class PasswordHashing {
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 128;
    private static Logger logger = Logger.getLogger(PasswordHashing.class);

    public static byte[] getSalt(){
        Random random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String getSaltedPasswordHash(String password, byte[] salt) throws AssertionError {
        return byteToString(getSaltedPasswordBytes(password, salt));
    }

    private static byte[] getSaltedPasswordBytes(String password, byte[] salt) throws AssertionError {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    public static String byteToString(byte[] input) {
        return org.apache.commons.codec.binary.Base64.encodeBase64String(input);
    }

    public static byte[] stringToByte(String input) {
        if (Base64.isBase64(input)) {
            return Base64.decodeBase64(input);

        } else {
            return Base64.encodeBase64(input.getBytes());
        }
    }
}
