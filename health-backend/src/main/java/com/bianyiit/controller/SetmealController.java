package com.bianyiit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bianyiit.constant.MessageConstant;
import com.bianyiit.constant.RedisConstant;
import com.bianyiit.entity.PageResult;
import com.bianyiit.entity.QueryPageBean;
import com.bianyiit.entity.Result;
import com.bianyiit.pojo.Setmeal;
import com.bianyiit.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;
import utils.QiniuUtils;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

@RequestMapping("/setmeal")
@RestController
public class SetmealController {
    @Reference
    SetmealService setmealService;
    @Autowired
    JedisPool jedisPool;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile")MultipartFile imgFile){
        System.out.println(imgFile);
        //获取原始的文件上传的名字,主要用来截取文件后缀
        String originalFilename = imgFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String extention = originalFilename.substring(index - 1);//.jpg
        String fileName = UUID.randomUUID().toString()+extention;//文件上传之后的名称
        try {
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
        return new Result(true,MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = setmealService.findPage(queryPageBean);
        return pageResult;
    }
    @RequestMapping("/add")
    public Result addSetmeal(@RequestBody Setmeal setmeal,Integer[] checkgroupIds){
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
        Result result = setmealService.addSetmeal(setmeal,checkgroupIds);
        return result;
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        Result result = setmealService.findById(id);
        return result;
    }
    @RequestMapping("/findCheckGroupIdBySetmeal")
    public Result findCheckGroupIdBySetmeal(Integer id){
        Result result = setmealService.findCheckGroupIdBySetmeal(id);
        return result;
    }
    @RequestMapping("/edit")
    public Result edit(@RequestBody Setmeal setmeal,Integer[] checkgroupIds){
        Result result = setmealService.edit(setmeal,checkgroupIds);
        return result;
    }
    @RequestMapping("/delete")
    public Result delete(Integer id){
        Result byId = setmealService.findById(id);
        Setmeal data =(Setmeal) byId.getData();
        String img=data.getImg();
        try {
            jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,img);
            jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_DB_RESOURCES,img);
            QiniuUtils.deleteFileFromQiniu(img);
            System.out.println("图片清理完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Result result = setmealService.delete(id);

        return result;
    }
}
