package util;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class PostUtil {
    public static String readBody(HttpServletRequest request) throws UnsupportedEncodingException {
        // 先去获取到 body 的长度(单位为字节)
        int length = request.getContentLength();
        byte[] buffer = new byte[length];
        try (InputStream inputStream = request.getInputStream()) {
            inputStream.read(buffer, 0, length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(buffer, "UTF-8");
    }
}