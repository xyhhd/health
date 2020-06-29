package com.bianyiit.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bianyiit.constant.MessageConstant;
import com.bianyiit.dao.MemberDao;
import com.bianyiit.dao.OrderDao;
import com.bianyiit.dao.OrderSettingDao;
import com.bianyiit.entity.Result;
import com.bianyiit.pojo.Member;
import com.bianyiit.pojo.Order;
import com.bianyiit.pojo.OrderSetting;
import com.bianyiit.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderSettingDao orderSettingDao;

    @Autowired
    MemberDao memberDao;

    /*
    * 提交预约信息
    * */
    @Override
    public Result addOrder(Map map) throws Exception{
        //获取当前时间，查看当前时间是否有预约
        String orderDate=(String)map.get("orderDate");
        //  获取前台发送的验证码手机号
        String telephone = (String)map.get("telephone");
        //将String转date
        Date date = DateUtils.parseString2Date(orderDate);

        OrderSetting orderSetting =orderSettingDao.findOrderSettingByOrderDate(date);
        if(orderSetting==null){
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        int number = orderSetting.getNumber();
        int reservations=orderSetting.getReservations();
        if(number==reservations){
            return new Result(false,MessageConstant.ORDER_FULL);
        }
        //判断用户是否是会员，如果不是则不能预约
        //通过手机号判断
        Member member=memberDao.findMenberByTel(telephone);
        if(member!=null){
            Integer memberId = member.getId();//会员ID
            Integer setmealId = Integer.parseInt((String)map.get("setmealId"));
            Order order = new Order(memberId,date,null,null,setmealId);
            List<Order> list = orderDao.findByCondition(order);
            if(list!=null&&list.size()>0){
                //该会员已经预约过，不能再次预约
                return new Result(false,MessageConstant.HAS_ORDERED);
            }
        }
        //可以预约  设置预约人数+1
        orderSetting.setReservations(orderSetting.getReservations()+1);
        orderSettingDao.updateOrderSetting(orderSetting);
        if(member==null){
            member = new Member();
            member.setName((String) map.get("name"));
            member.setPhoneNumber(telephone);
            member.setIdCard((String) map.get("idCard"));
            member.setSex((String) map.get("sex"));
            member.setRegTime(new Date());
            memberDao.addMember(member);
        }
        //将预约信息保存到预约表里面
        Order order = new Order(member.getId(),date,(String)map.get("orderType"),Order.ORDERSTATUS_NO,Integer.parseInt((String)map.get("setmealId")));
        orderDao.addOrder(order);
        return new Result(true,MessageConstant.ORDER_SUCCESS,order.getId());
    }

    @Override
    public Map findById(Integer id) throws Exception {
        Map map=orderDao.findById(id);
        if(map!=null){
            Date orderDate = (Date) map.get("orderDate");
            map.put("orderDate",DateUtils.parseDate2String(orderDate));
        }
        return map;
    }
}
