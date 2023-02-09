package gov.iti.jets.persistence.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {
    private  final static byte []salt="B@37000169".getBytes();
    public static String doHahing(String password) {
        MessageDigest md;
        StringBuilder hashedPassword;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPasswordByte = md.digest(password.getBytes());
            hashedPassword = new StringBuilder();
            for (byte b : hashedPasswordByte) {
                hashedPassword.append(String.format("%02x",b));

            }
            return hashedPassword.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
            
    }
}
