package manager.java.sql.connect.test;

import manager.java.encryption.Encrytion;

import javax.crypto.SecretKey;

public class test2 {
    public static void main(String[] args) throws Exception {
        SecretKey key = Encrytion.generateKey();
        int new_age = Encrytion.AESEncrypt(20, key);
        System.out.println(new_age);
        int old_age = Encrytion.AESDecrypt(new_age, key);
        System.out.println(old_age);
    }
}
