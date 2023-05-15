package com.gcu.baima.service.Back;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author xiu
 * @create 2023-05-15 15:44
 */
public interface OssService {
    String uploadFile(MultipartFile multipartFile);
}
