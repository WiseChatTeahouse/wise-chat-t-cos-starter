package chat.wise.file.cos.common.model;

import com.qcloud.cos.model.PutObjectResult;
import lombok.Data;

/**
 * @Author siberia.hu
 * @Package chat.wise.file.cos.common.domain
 * @Date 2024/6/18 22:13
 */
@Data
public class FilePutResult {

    /*上传文件时生成的文件名*/
    private String fileName;

    private PutObjectResult putObjectResult;

}
