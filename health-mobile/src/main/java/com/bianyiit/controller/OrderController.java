package com.bianyiit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bianyiit.constant.MessageConstant;
import com.bianyiit.constant.RedisMessageConstant;
import com.bianyiit.entity.Result;
import com.bianyiit.pojo.Order;
import com.bianyiit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    JedisPool jedisPool;

    @Reference
    OrderService orderService;

    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map) throws Exception {
        //获取前台发送的验证码手机号
        String telephone = (String)map.get("telephone");
        try {
            //后获取在Redis中存储的验证码
            String s = jedisPool.getResource().get(telephone+ RedisMessageConstant.SENDTYPE_ORDER);
            System.out.println(telephone+ RedisMessageConstant.SENDTYPE_ORDER);
            //获取前台发送的验证码
            String validateCode = (String)map.get("validateCode");
            System.out.println(validateCode);
            System.out.println(s);
            if(s==null||!s.equals(validateCode)){
                System.out.println(1);
                return new Result(false, MessageConstant.VALIDATECODE_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        map.put("orderType", Order.ORDERTYPE_WEIXIN);
        Result result=orderService.addOrder(map);
        return result;
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            Map map=orderService.findById(id);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }

    }
}
