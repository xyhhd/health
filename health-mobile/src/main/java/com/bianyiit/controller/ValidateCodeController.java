package com.bianyiit.controller;

import com.aliyuncs.exceptions.ClientException;
import com.bianyiit.constant.MessageConstant;
import com.bianyiit.constant.RedisMessageConstant;
import com.bianyiit.entity.Result;
import com.bianyiit.utils.SMSUtils;
import com.bianyiit.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    JedisPool jedisPool;
    @RequestMapping("/sendCode")
    public Result sendCode(String telephone){
        Integer code = ValidateCodeUtils.generateValidateCode(4);//生成4位数字验证码
        /*try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());
        } catch (ClientException e) {
            e.printStackTrace();
            //发送验证码失败
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }*/
        System.out.println("发送到手机验证码是:" + code);
        //将生成的验证码缓存到redis,并指定过期时间
        //jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_ORDER,5*60,code.toString());
        jedisPool.getResource().setex("13397387223001",5*60,"1234");
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
    @RequestMapping("/sendLogin")
    public Result sendLogin(String telephone){
        Integer code = ValidateCodeUtils.generateValidateCode(4);//生成4位数字验证码
        /*try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());
        } catch (ClientException e) {
            e.printStackTrace();
            //发送验证码失败
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }*/
        System.out.println("发送到手机验证码是:" + code);
        //将生成的验证码缓存到redis,并指定过期时间
        /*jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_LOGIN,5*60,code.toString());*/
        jedisPool.getResource().setex("13397387223002",5*60,"1234");
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}

