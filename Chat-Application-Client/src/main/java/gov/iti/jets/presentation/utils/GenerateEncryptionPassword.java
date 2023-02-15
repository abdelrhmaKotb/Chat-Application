package gov.iti.jets.presentation.utils;





import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class GenerateEncryptionPassword {

    public static final String AES = "AES";

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    private static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }

    public static String  encrypte(String phoneNumber,String password) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
        String key = "F21E2A7FB6C68037FAEAA55222E320F7";


        byte[] bytekey = hexStringToByteArray(key);
        SecretKeySpec sks = new SecretKeySpec(bytekey, GenerateEncryptionPassword.AES);
        Cipher cipher = Cipher.getInstance(GenerateEncryptionPassword.AES);
        cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());
        byte[] encrypted = cipher.doFinal(password.getBytes());
        String encryptedpwd = byteArrayToHexString(encrypted);

        try {
            File myObj = new File("./src/main/resources/keypassword.txt");
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } else {
              System.out.println("File already exists.");
            }
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

          
          try {
            FileWriter myWriter = new FileWriter("./src/main/resources/keypassword.txt");
            myWriter.write("PhoneNumber="+phoneNumber+"\n");
            myWriter.write("Encrypted_Password="+encryptedpwd+"\n");

            myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        return (encryptedpwd);

    }
}
