package tahub.sdapitahub.Utils;

import tahub.sdapitahub.entity.JobRequirement;
import tahub.sdapitahub.entity.TaUser;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class TokenUtil {

    private static final String SECRET_KEY_ALGORITHM = "AES";
    private static final int KEY_LENGTH_IN_BITS = 128;
    private static final int KEY_LENGTH_IN_BYTES = KEY_LENGTH_IN_BITS / 8;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnowbhfsdbcs807612734623742343287423pqrstuvwxyz0123456789";
    private static final int LENGTH = 30;

    private static SecretKey secretKey;

    static {
        byte[] keyBytes = new byte[KEY_LENGTH_IN_BYTES];
        SecureRandom random = new SecureRandom();
        random.nextBytes(keyBytes);
        secretKey = new SecretKeySpec(keyBytes, SECRET_KEY_ALGORITHM);
    }

    public static String encryptToken(String token) {
        try {
            Cipher cipher = Cipher.getInstance(SECRET_KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(token.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting token", e);
        }
    }

    public static String generateRandomString() {
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    public static boolean isResetTokenValid(TaUser user, String token) {
        String validToken = user.getResetToken();
        return validToken.equals(token);
    }

    public static boolean isApprovalTokenValid(JobRequirement jobRequirement, String token) {
        String validToken = jobRequirement.getApprovalToken();
        return validToken.equals(token);
    }

}
