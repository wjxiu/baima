package com.gcu.baima.controller;

import com.gcu.baima.service.Back.OssService;
import com.gcu.baima.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传控制器，上传到aliyun的oss中
 *
 * @author xiu
 * @create 2023-05-15 15:42
 */
@Api(tags = "上传图片控制器")
@RestController
@RequestMapping("/baima/oss")
public class OssController {
    @Autowired
    private OssService ossService;

    //上传图片的地址
    @ApiOperation(value = "上传图片", notes = "返回图片地址")
    @PostMapping()
    public R uploadOssFile(@RequestParam("file") MultipartFile multipartFile) {
        String url = ossService.uploadFile(multipartFile);
        return R.ok().data("url", url);
    }
}
