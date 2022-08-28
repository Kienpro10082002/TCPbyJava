package Server;

import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class algorithmDES {

    public static String DesEncrypt(String str, String secretKey) {
        try {
            byte[] keyBytes = secretKey.getBytes();
            byte[] data = str.getBytes();

            //Tạo khóa mã hóa
            DESKeySpec keySpec = new DESKeySpec(keyBytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);
            /*
            Tạo đối tượng Cipher (đối tượng này dùng để mã hóa):
            + Thuật toán Des.
            + Chế độ CBC (Cipher block chaning): Electronic Codebook mode.
            + Padding scheme: PKCS5Padding = PKCS #5-style padding.
            */
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            
            //Mã hóa
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(keySpec.getKey()));

            byte[] result = cipher.doFinal(data);
            String encrypt = Base64.getEncoder().encodeToString(result);
            return encrypt;
        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        return null;
    }

    public static String DesDecrypt(String str, String secretKey) {
        try {
            byte[] keyBytes = secretKey.getBytes();
            byte[] data = Base64.getDecoder().decode(str);
            
            //Tạo khóa giải mã
            DESKeySpec keySpec = new DESKeySpec(keyBytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);

            /*
            Tạo đối tượng Cipher (đối tượng này dùng để mã hóa):
            + Thuật toán Des.
            + Chế độ CBC (Cipher block chaning): Electronic Codebook mode.
            + Padding scheme:PKCS5Padding = PKCS #5-style padding.
            */
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            
            //Giải mã
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(keyBytes));

            byte[] result = cipher.doFinal(data);
            return new String(result);
        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        return null;
    }


}