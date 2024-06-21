package chat.wise.file.cos.common.utils;

import java.util.UUID;

/**
 * @Author siberia.hu
 * @Package chat.wise.file.cos.common.utils
 * @Date 2024/6/18 21:44
 */
public class FileNameUtil {

    public static String randomGetFileName() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
