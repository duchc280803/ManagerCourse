package com.example.managercourse.util;

public class GenerateUsernameUtil {

    public static String generateUsername(String fullName) {
        // Loại bỏ dấu và chuyển đổi thành chữ viết thường
        String username = fullName.replaceAll("\\p{M}", "").toLowerCase();
        // Thay các khoảng trắng bằng dấu gạch dưới
        username = username.replaceAll("\\s+", "_");
        // Hoặc, bạn cũng có thể loại bỏ các khoảng trắng
        // username = username.replaceAll("\\s+", "");
        return username;
    }
}
