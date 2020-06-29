package com.bianyiit.jobs;

import com.bianyiit.constant.RedisConstant;
import com.bianyiit.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import redis.clients.jedis.JedisPool;

import java.util.Set;
@Controller
public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;
    public void clearImg(){
        Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if(set!=null){
            for (String s : set) {
                QiniuUtils.deleteFileFromQiniu(s);
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,s);
                System.out.println("图片清理完成");
            }
        }
    }
}
