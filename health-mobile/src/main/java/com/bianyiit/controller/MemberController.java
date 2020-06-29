package com.bianyiit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.bianyiit.constant.MessageConstant;
import com.bianyiit.constant.RedisMessageConstant;
import com.bianyiit.entity.Result;
import com.bianyiit.pojo.Member;
import com.bianyiit.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Reference
    MemberService memberService;
    @Autowired
    JedisPool jedisPool;
    @RequestMapping("/login")
    public Result login(@RequestBody Map map, HttpServletResponse response){
        //获取页面上输入的验证码
        String telephone = (String)map.get("telephone");
        //获取验证码
        String validateCode = (String)map.get("validateCode");
        String code = jedisPool.getResource().get(telephone+ RedisMessageConstant.SENDTYPE_LOGIN);
        if(code==null||!code.equals(validateCode)){
            //验证码错误
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        Member member=memberService.findMemberByTel(telephone);
        if(member==null){
            member = new Member();
            member.setPhoneNumber(telephone);
            member.setRegTime(new Date());
            memberService.addMember(member);
        }
        //登录成功，用cookie保存起来
        Cookie cookie = new Cookie("login_member_telephone",telephone);
        cookie.setPath("/");
        cookie.setMaxAge(30*60);//设置cookie的有效期是30天
        //保存会员信息到Redis中
        String json = JSON.toJSON(member).toString();
        jedisPool.getResource().setex("telephone",30*60,json);

        //cookie存在页面中
        response.addCookie(cookie);
        return new Result(true,MessageConstant.LOGIN_SUCCESS);
    }
}
