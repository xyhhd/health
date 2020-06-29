/*
package com.bianyiit.test;

import com.bianyiit.utils.QiniuUtils;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class QiNiYunTest {
    @Test
    public void test01() {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "LlyhkkETvlJ3OGXRVhaPUmgcA5ZvT5xWJIFEcVpj";
        String secretKey = "2SYbzC19AhDr3cWUzMCgSIruOTxJwOhVXWKm8iZs";
        String bucket = "xyhhd";//创建的存储空间的名称
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "C:\\Users\\18754\\Pictures\\少女\\少女.PNG";
        //上传文件的名称，默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "起飞";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }

    }
    @Test
    public void test02() {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        //...生成上传凭证，然后准备上传
        String accessKey = "LlyhkkETvlJ3OGXRVhaPUmgcA5ZvT5xWJIFEcVpj";
        String secretKey = "2SYbzC19AhDr3cWUzMCgSIruOTxJwOhVXWKm8iZs";
        //创建的存储空间的名称
        String bucket = "xyhhd";
        String key = "起飞";
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

    @Test
    public void test03() throws Exception {
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File("C:\\Users\\18754\\Desktop\\xy.xlsx")));
        XSSFSheet sheet = excel.getSheetAt(0);
        for (Row cells : sheet) {
            for (Cell cell : cells) {
                System.out.println(cell.getStringCellValue());
            }
            excel.close();
        }

    }

}
*/
