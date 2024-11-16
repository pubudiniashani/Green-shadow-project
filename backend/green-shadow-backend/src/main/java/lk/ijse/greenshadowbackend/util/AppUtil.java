package lk.ijse.greenshadowbackend.util;

import java.util.Base64;

public class AppUtil {
    public static String imageToBase64(byte [] profilePic){
        return Base64.getEncoder().encodeToString(profilePic);
    }
}
