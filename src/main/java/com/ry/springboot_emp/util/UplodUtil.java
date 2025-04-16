package com.ry.springboot_emp.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author 21142
 * @version 1.0
 * @description: TODO
 */
public class UplodUtil {

    //阿里云oss域名
    public static final String ALI_DOMAIN="https://lmy-lmy.oss-cn-beijing.aliyuncs.com/";


    //添加图片
    public static String uploadImage(MultipartFile file) throws IOException {
        // 生成新文件名
        String originalFilename = file.getOriginalFilename();//原来图片名
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID() + ext;
        //地狱节点
        String endpoint="http://oss-cn-beijing.aliyuncs.com";
        String accessKeyId="LTAI5tHNyxv96svWE2XikYLm";
        String accessKeySecret= "3EAEbu0dpKxmx42xTEqZ7GABIrDllh";
        //oss客户端对象
        OSS ossClient=new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        ossClient.putObject(
                "lmy-lmy", //仓库名
                fileName, //文件名
                file.getInputStream()
        );
        ossClient.shutdown(); //关闭
        return ALI_DOMAIN+fileName;
    }



}
