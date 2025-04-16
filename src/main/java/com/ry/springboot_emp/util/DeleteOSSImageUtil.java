package com.ry.springboot_emp.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.DeleteObjectsRequest;

import java.io.IOException;
import java.util.List;

/**
 * @author 21142
 * @version 1.0
 * @description: TODO
 */
public class DeleteOSSImageUtil {

    public static final String ALI_DOMAIN="https://lmy-lmy.oss-cn-beijing.aliyuncs.com/"; //阿里云oss域名
    public static final String endpoint="http://oss-cn-beijing.aliyuncs.com";//地域节点
    public static final String accessKeyId="LTAI5tHNyxv96svWE2XikYLm";  //访问密钥ID
    public static final String accessKeySecret= "3EAEbu0dpKxmx42xTEqZ7GABIrDllh"; //访问密钥
    public static final String BUCKET_NAME="lmy-lmy";//存储空间名称
    //删除单个
    public static void deleteImage(String FILENAME) throws IOException {
        //oss对象
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try{
            //删除文件
        ossClient.deleteObject(BUCKET_NAME,FILENAME); //仓库名，文件名
        }finally {
            ossClient.shutdown(); //关闭
        }
    }
    //批量删除
    public static void deleteImages(List<String> ObjectName){
        if (ObjectName == null || ObjectName.isEmpty()) {
            return; // 返回空列表请求
        }
        //oss对象
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try{
            DeleteObjectsRequest request=new DeleteObjectsRequest(BUCKET_NAME).withKeys(ObjectName).withQuiet(false);
            //执行删除
            ossClient.deleteObjects(request);
        }finally {
            ossClient.shutdown();
        }
    }
}
