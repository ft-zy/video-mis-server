//package com.zy.web.video_management.videoInfo.service;
//
//import com.aliyun.oss.OSS;
//import com.aliyun.oss.OSSClientBuilder;
//import com.aliyun.oss.model.CannedAccessControlList;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.UUID;
//
//@Service
//public class OssUploadService {
//
//
//    public static void main(String[] args) {
//        String uploadfile = uploadfile(new File("F:\\video_web\\img\\test.jpg"));
//        System.out.println(uploadfile);
//    }
//
//
//    public static String uploadfile(File multipartFile) {
//        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
//        String endpoint = "oss-cn-chengdu.aliyuncs.com";
//        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
//        String accessKeyId = "LTAI5tGA8uheWcKgikKNFLjn";
//        String accessKeySecret = "IyhfzwA3rCw7L2sdhg0O8nN8QCO2Vp";
//        // 填写Bucket名称，例如examplebucket。
//        String bucketName = "zyvideosys";
//        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
//
//        // 创建OSSClient实例。
//        OSS ossClient = null;
//
//        try {
//            ossClient = new  OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//            if (!ossClient.doesBucketExist(bucketName)) {
//                // 创建 bucket
//                ossClient.createBucket(bucketName);
//                // 设置 实例访问权限，公共读
//                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
//            }
//            // 获取文件上传流
//            InputStream inputStream = new FileInputStream((File) multipartFile);
//
//            // 构建目录日期
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
//            String datePath = simpleDateFormat.format(new Date());
//
//            // 获取文件名
//            String originname = multipartFile.getName();
//            String filename = UUID.randomUUID().toString();
//            String suffix = originname.substring(originname.lastIndexOf("."));
//            String newName = filename + suffix;
//            String fileUrl =  datePath + "/" + newName;
//
//
//            // 文件上传到阿里云服务器
//            ossClient.putObject(bucketName, fileUrl, inputStream);
//
//            return "https://" + bucketName + "." + endpoint + "/" + fileUrl;
//        }catch (Exception ce) {
//            //System.out.println("Caught an ClientException, which means the client encountered "
//            //        + "a serious internal problem while trying to communicate with OSS, "
//            //        + "such as not being able to access the network.");
//            //System.out.println("Error Message:" + ce.getMessage());
//            return "fail";
//        } finally {
//            if (ossClient != null) {
//                ossClient.shutdown();
//            }
//        }
//    }
//
//}
