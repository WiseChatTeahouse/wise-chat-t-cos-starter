package chat.wise.file.cos.service;

import chat.wise.file.cos.common.model.FilePutResult;
import com.qcloud.cos.model.Bucket;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author siberia.hu
 * @Package chat.wise.file.cos.service
 * @Date 2024/6/17 21:42
 */
public interface FileService {

    /**
     * 获取所有桶
     *
     * @return result
     */
    List<Bucket> getBucketList();

    /**
     * 文件上传
     *
     * @param file 文件
     * @return
     * @throws IOException
     */
    FilePutResult uploadFile(MultipartFile file) throws IOException;

    /**
     * 根据userID上传文件
     *
     * @param file 文件
     * @param uid  userID
     * @return
     * @throws IOException
     */
    FilePutResult uploadFile(MultipartFile file, long uid) throws IOException;
}
