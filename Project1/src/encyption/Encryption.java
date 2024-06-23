package encyption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encryption {

    public static SecretKey generateKey() throws Exception {
        String keyString = "TuongVanMinhTien";
        byte[] keyBytes = keyString.getBytes();
        return new SecretKeySpec(keyBytes, "AES");
    }

    public static String AESEncrypt(String dataToEncrypt, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(dataToEncrypt.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static int AESEncrypt(int x, SecretKey secretKey) throws Exception {
        String stringKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        int count = 0;
        for (int i = 0; i < stringKey.length(); i++) {
            if (Character.isUpperCase(stringKey.charAt(i))) {
                count++;
            }
        }
        return 3 * x * x - count * x + 5;

    }

    public static String AESDecrypt(String encryptedData, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }

    public static int AESDecrypt(int y, SecretKey secretKey) throws Exception {
        String stringKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        int count = 0;
        for (int i = 0; i < stringKey.length(); i++) {
            if (Character.isUpperCase(stringKey.charAt(i))) {
                count++;
            }
        }

        int delta = count * count + 12*y - 60;
        return (int) ((count + Math.sqrt(delta)) / 6);
    }

}