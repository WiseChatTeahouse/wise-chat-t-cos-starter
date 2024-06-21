package chat.wise.file.cos.service.impl;

import chat.wise.file.cos.TCosProperties;
import chat.wise.file.cos.common.constants.ExceptionConstant;
import chat.wise.file.cos.common.constants.TCOSConstant;
import chat.wise.file.cos.common.exception.TCOSException;
import chat.wise.file.cos.common.model.FilePutResult;
import chat.wise.file.cos.common.utils.FileNameUtil;
import chat.wise.file.cos.service.FileService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.Bucket;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author siberia.hu
 * @Package chat.wise.file.cos.service.impl
 * @Date 2024/6/17 21:42
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private COSClient cosClient;
    @Resource
    private TCosProperties tCosProperties;

    @Override
    public List<Bucket> getBucketList() {
        return cosClient.listBuckets();
    }

    @Override
    public FilePutResult uploadFile(MultipartFile file) throws IOException {
        if (null == file) {
            throw new TCOSException(ExceptionConstant.FILE_IS_NULL);
        }
        // 获取文件名称
        String filename = file.getOriginalFilename();
        String fileNameUUID = FileNameUtil.randomGetFileName();
        String[] split;
        if (null != filename) {
            split = filename.split("\\.");
            if (0 < split.length) {
                fileNameUUID = fileNameUUID + split[split.length - 1];
            }
        }

        InputStream inputStream = file.getInputStream();

        // 设置文件相关源信息
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.addUserMetadata(TCOSConstant.FILE_NAME, filename);

        // 上传
        PutObjectRequest putObjectRequest = new PutObjectRequest(tCosProperties.getBucket(), fileNameUUID, inputStream, objectMetadata);
        PutObjectResult result = cosClient.putObject(putObjectRequest);

        FilePutResult filePutResult = new FilePutResult();
        filePutResult.setFileName(fileNameUUID);
        filePutResult.setPutObjectResult(result);

        // 关闭流释放资源
        inputStream.close();
        return filePutResult;
    }

    @Override
    public FilePutResult uploadFile(MultipartFile file, long uid) throws IOException {


        return null;
    }
}
