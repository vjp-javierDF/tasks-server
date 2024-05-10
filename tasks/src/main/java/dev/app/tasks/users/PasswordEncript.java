package dev.app.tasks.users;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncript {
    // Encrypt password
    public String encryptPassword(String password)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (password == null || "".equals(password)) {
            return null;
        }
        return getString(getHash(password));
    }
    // method to convert byte array to string
    private static String getString(byte[] data) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16)
                    .toUpperCase().substring(1));

        }
        return sb.toString();
    }
    // method to get hash
    private static byte[] getHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        byte[] input = digest.digest(password.getBytes("UTF-8"));
        return input;
    }
}
