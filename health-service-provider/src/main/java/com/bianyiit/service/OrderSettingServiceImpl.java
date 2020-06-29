package com.bianyiit.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bianyiit.dao.OrderSettingDao;
import com.bianyiit.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service(interfaceClass = OrderSettingService.class)
public class OrderSettingServiceImpl implements OrderSettingService{
    @Autowired
    OrderSettingDao orderSettingDao;
    @Override
    public void addOrderSetting(List<OrderSetting> list) {
        if (list!=null){
            for (OrderSetting orderSetting : list) {
                Integer count=orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if(count>0){
                    orderSettingDao.updateOrderSetting(orderSetting);
                }else{
                    orderSettingDao.insertOrderSetting(orderSetting);
                }

            }

        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String start = date+"-1";
        String end = date+"-31";
        List<OrderSetting> list=orderSettingDao.getOrderSettingByMonth(start,end);
        List<Map> maps = new ArrayList<>();
        if (list!=null){
            for (OrderSetting orderSetting : list) {
                Map<String,Object> map = new HashMap<>();
                map.put("date",orderSetting.getOrderDate().getDate());
                map.put("number",orderSetting.getNumber());
                map.put("reservations",orderSetting.getReservations());
                maps.add(map);
            }
        }
        return maps;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        Integer count=orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if(count>0){
            orderSettingDao.updateOrderSetting(orderSetting);
        }else{
            orderSettingDao.insertOrderSetting(orderSetting);
        }
    }
}
