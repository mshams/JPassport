/*
 * Copyright (c) 2021. By Muhammad Shams
 * @author Muhammad Shams - mshamsj@gmail.com
 * @version 1.0
 */

package security;

import io.ipfs.multibase.Multibase;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.net.InetAddress;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

/***
 * Encryption and Decryption of String data; PBE(Password Based Encryption and Decryption)
 *
 * @author Vikram, M.Shams
 */
public class CryptoUtil {

    private static Cipher ecipher;
    private static Cipher dcipher;
    private static String key;

    // 8-byte Salt
    private static byte[] salt = {
            (byte) 0xA9, (byte) 0x1B, (byte) 0xC9, (byte) 0x33,
            (byte) 0x55, (byte) 0x36, (byte) 0xE2, (byte) 0x04
    };

    // Iteration count
    private static int iterationCount = 5;

    private CryptoUtil() {
        //not allowed to make instance
    }

    private static String secretKey() {
        if (key != null)
            return key;

        //generate secretKey based on hostname
        String hostname = "hostname";
        try {
            hostname = InetAddress.getLocalHost().getHostName();
            key = encryptWithKey(hostname, hostname);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return key;
    }

    public static String encrypt(String plainText) {
        return encryptWithKey(secretKey(), plainText);
    }

    /**
     * @param secretKey Key used to encrypt data
     * @param plainText Text input to be encrypted
     * @return Returns encrypted text
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.spec.InvalidKeySpecException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidKeyException
     * @throws java.security.InvalidAlgorithmParameterException
     * @throws java.io.UnsupportedEncodingException
     * @throws javax.crypto.IllegalBlockSizeException
     * @throws javax.crypto.BadPaddingException
     */
    private static String encryptWithKey(String secretKey, String plainText) {
        String encStr = "ERROR";
        try {
            //Key generation for enc and desc
            KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            // Prepare the parameter to the ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

            //Enc process
            ecipher = Cipher.getInstance(key.getAlgorithm());
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

            String charSet = "UTF-8";
            byte[] in = plainText.getBytes(charSet);
            byte[] out = ecipher.doFinal(in);
            encStr = Multibase.encode(Multibase.Base.Base58BTC, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encStr;
    }

    /**
     * @param encryptedText encrypted text input to decrypt
     * @return Returns plain text after decryption
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.spec.InvalidKeySpecException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidKeyException
     * @throws java.security.InvalidAlgorithmParameterException
     * @throws java.io.UnsupportedEncodingException
     * @throws javax.crypto.IllegalBlockSizeException
     * @throws javax.crypto.BadPaddingException
     */
    public static String decrypt(String encryptedText) {
        String plainStr = "ERROR";

        try {
            //Key generation for enc and desc
            KeySpec keySpec = new PBEKeySpec(secretKey().toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            // Prepare the parameter to the ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
            //Decryption process; same key will be used for decr
            dcipher = Cipher.getInstance(key.getAlgorithm());
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
            byte[] enc = Multibase.decode(encryptedText);
            byte[] utf8 = dcipher.doFinal(enc);
            String charSet = "UTF-8";
            plainStr = new String(utf8, charSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plainStr;
    }

    /*
    public static void main(String[] args) throws Exception {

        String plain = "This is an important message";
        String enc = encrypt(plain);
        System.out.println("Original text: " + plain);
        System.out.println("Encrypted text: " + enc);
        String plainAfter = decrypt(enc);
        System.out.println("Original text after decryption: " + plainAfter);
    }
    */
}
