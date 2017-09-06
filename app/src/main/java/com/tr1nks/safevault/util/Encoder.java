package com.tr1nks.safevault.util;

import android.util.Log;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encoder {
    private static final String AES = "AES";
    private static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";

    public static byte[] decode(byte[] password, byte[] data) {
        return code(password, data, Cipher.DECRYPT_MODE);
    }

    public static byte[] encode(byte[] password, byte[] data) {
        return code(password, data, Cipher.ENCRYPT_MODE);
    }

    private static byte[] code(byte[] password, byte[] data, int mode) {
        try {
            //        SecretKeySpec secretKeySpec = new SecretKeySpec(password,0,password.length, AES);
            SecretKeySpec secretKeySpec = new SecretKeySpec(password, AES);
//            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(mode, secretKeySpec);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            Log.e("Encoder:", Log.getStackTraceString(e));
            return null;
        }
    }

    public static byte[] preparePassw(byte[] password) {
        byte[] out = new byte[32];
        int j = 0;
        for (int i = 0; i < out.length; i++) {
            out[i] = password[j];
            j++;
            if (j == password.length) {
                j = 0;
            }
        }
        return out;
    }
}
/*public class ActivityEncrypt extends Activity {

    private String encryptedFileName = "Enc_File2.txt";
    private static String algorithm = "AES";
    static SecretKey yourKey = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveFile("Hello From CoderzHeaven testing ");
        decodeFile();

    }

    public static SecretKey generateKey(char[] passphraseOrPin, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Number of PBKDF2 hardening rounds to use. Larger values increase
        // computation time. You should select a value that causes computation
        // to take >100ms.
        final int iterations = 1000;

        // Generate a 256-bit key
        final int outputKeyLength = 256;

        SecretKeyFactory secretKeyFactory = SecretKeyFactory
                .getInstance("PBKDF2WithHmacSHA1");
        KeySpec keySpec = new PBEKeySpec(passphraseOrPin, salt, iterations,
                outputKeyLength);
        yourKey = secretKeyFactory.generateSecret(keySpec);
        return yourKey;
    }

    public static SecretKey generateSalt() throws NoSuchAlgorithmException {
        // Generate a 256-bit key
        final int outputKeyLength = 256;

        SecureRandom secureRandom = new SecureRandom();
        // Do *not* seed secureRandom! Automatically seeded from system entropy.
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(outputKeyLength, secureRandom);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    public static byte[] encodeFile(SecretKey yourKey, byte[] fileData)            throws Exception {
        byte[] data = yourKey.getEncoded();
        SecretKeySpec skeySpec = new SecretKeySpec(data, 0, data.length,                algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        byte[] encrypted = cipher.doFinal(fileData);

        return encrypted;
    }

    public static byte[] decodeFile(SecretKey yourKey, byte[] fileData)            throws Exception {
        byte[] data = yourKey.getEncoded();
        SecretKeySpec skeySpec = new SecretKeySpec(data, 0, data.length,                algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);

        byte[] decrypted = cipher.doFinal(fileData);

        return decrypted;
    }

    void saveFile(String stringToSave) {
        try {
            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator, encryptedFileName);
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(file));
            char[] p = { 'p', 'a', 's', 's' };
            SecretKey yourKey = generateKey(p, generateSalt().toString()
                    .getBytes());
            byte[] filesBytes = encodeFile(yourKey, stringToSave.getBytes());
            bos.write(filesBytes);
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void decodeFile() {
        try {
            byte[] decodedData = decodeFile(yourKey, readFile());
            String str = new String(decodedData);
            System.out.println("DECODED FILE CONTENTS : " + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] readFile() {
        byte[] contents = null;

        File file = new File(Environment.getExternalStorageDirectory()
                + File.separator, encryptedFileName);
        int size = (int) file.length();
        contents = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(
                    new FileInputStream(file));
            try {
                buf.read(contents);
                buf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return contents;
    }

}*/