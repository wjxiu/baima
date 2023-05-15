package com.gcu.baima.service.Back.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.gcu.baima.service.Back.OssService;
import com.gcu.baima.utils.ConstantProperties;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author xiu
 * @create 2023-05-15 15:44
 */
@Service
public class OssServiceImpl implements OssService {
    //上传文件
    @Override
    public String uploadFile(MultipartFile file) {
        String url = "";
        System.out.println(ConstantProperties.END_POINT);
        OSS ossClient = new OSSClientBuilder().build(ConstantProperties.END_POINT, ConstantProperties.ACCESS_KEY_ID, ConstantProperties.ACCESS_KEY_SECRET);
        try {
            String name = UUID.randomUUID().toString().substring(0, 11) + file.getOriginalFilename();
            String datepath = new DateTime().toString("yyyy/MM/dd");
            InputStream inputStream = file.getInputStream();
            String filename = datepath + "/" + name;
            ossClient.putObject(ConstantProperties.BUCKET_NAME, filename, inputStream);
            url = ConstantProperties.BASEPATH + "/" + filename;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return url;
    }
}
