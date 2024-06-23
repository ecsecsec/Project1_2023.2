package test;

import javax.crypto.SecretKey;

import encyption.Encryption;
import encyption.hash;

public class testEncrypt {
	public static void main(String[] args) {
        try {
            SecretKey secretKey = Encryption.generateKey();
            String encryptedString = Encryption.AESEncrypt("CountDown", secretKey);
            System.out.println("Encrypted String: " + encryptedString);
            System.out.println(Encryption.AESEncrypt("Ho Hoan Kiem", secretKey));
            System.out.println(Encryption.AESEncrypt("Merry Christmas", secretKey));
            String decryptedPass = Encryption.AESDecrypt("PimVNutPWs8AE+Or3kvsRQ==", Encryption.generateKey());
            System.out.println(decryptedPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s = "123";
        hash hasher = new hash();
        String hashedValue = hasher.SHA256pass(s);
        System.out.println("Giá trị băm của chuỗi '" + s + "': " + hashedValue);
    }
}
