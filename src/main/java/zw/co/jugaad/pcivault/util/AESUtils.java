package zw.co.jugaad.pcivault.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESUtils {

    private static final String AES_ALGORITHM = "AES";
    private static final String AES_CIPHER_MODE = "AES/CBC/PKCS5Padding";
    private static final int KEY_SIZE = 256;

    public static String encrypt(String plainText, String key, String iv) throws Exception {
        byte[] encryptedBytes;

        // Generate AES key
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), AES_ALGORITHM);

        // Initialize Cipher with CBC mode and IV
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
        Cipher cipher = Cipher.getInstance(AES_CIPHER_MODE);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        // Perform encryption
        byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        // Encode encrypted data to Base64 string
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String encryptedText, String key, String iv) throws Exception {
        byte[] decryptedBytes;

        // Generate AES key
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), AES_ALGORITHM);

        // Initialize Cipher with CBC mode and IV
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
        Cipher cipher = Cipher.getInstance(AES_CIPHER_MODE);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        // Decode Base64 string to encrypted data
        byte[] encryptedData = Base64.getDecoder().decode(encryptedText);

        // Perform decryption
        byte[] decrypted = cipher.doFinal(encryptedData);

        return new String(decrypted, StandardCharsets.UTF_8);
    }

}