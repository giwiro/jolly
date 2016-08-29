package app.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Gi Wah Davalos on 28/08/2016.
 */
public class Hasher {

    public static String MD5(String pt) throws NoSuchAlgorithmException {

        String salt = "salitre";
        String salted = pt + salt;

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(salted.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();

    }

}

