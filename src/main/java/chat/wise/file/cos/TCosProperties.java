package chat.wise.file.cos;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author siberia.hu
 * @Package chat.wise.file.cos
 * @Date 2024/6/17 21:41
 */
@Data
@ConfigurationProperties(prefix = "wise.chat.file.cos")
public class TCosProperties {
    private String bucket; // 桶
    private String region; // 桶所属地域

    private String secretId;
    private String secretKey;
    private String sessionToken;
}
