package chat.wise.file.cos;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author siberia.hu
 * @Package chat.wise.file.cos
 * @Date 2024/6/17 21:41
 */
@Slf4j
// 扫包
@ComponentScan
// 读取配置文件
@EnableConfigurationProperties(TCosProperties.class)
// 属性不存在，则不加载
@ConditionalOnProperty(name = "wise.chat.file.cos.secretId", matchIfMissing = false)
public class TCosAutoConfiguration {
    private final TCosProperties tCosProperties;

    public TCosAutoConfiguration(TCosProperties tCosProperties) {
        this.tCosProperties = tCosProperties;
    }

    @Bean(name = "cosClient")
    public COSClient initCos() {
        log.info("====== 开始初始化COS文件系统 ======");
        // 1 初始化用户身份信息（secretId, secretKey）。
        // SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        COSCredentials cred = new BasicCOSCredentials(this.tCosProperties.getSecretId(), this.tCosProperties.getSecretKey());
        log.info("====== COS文件系统(ง •̀_•́)ง‼ ======");
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(this.tCosProperties.getRegion());
        ClientConfig clientConfig = new ClientConfig(region);
        log.info("====== COS文件系统( ◠‿◠ ) ======");
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        log.info("====== COS文件系统创建完成 ======");
        return cosClient;
    }
}
