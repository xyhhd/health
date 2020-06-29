package com.bianyiit.dao;

import com.bianyiit.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {
    public void addOrderSetting(List<OrderSetting> list);

    public Integer findCountByOrderDate(Date orderDate);

    public void updateOrderSetting(OrderSetting orderSetting);

    public void insertOrderSetting(OrderSetting orderSetting);

    public List<OrderSetting> getOrderSettingByMonth(@Param("param1") String start, @Param("param2")String end);

    public OrderSetting findOrderSettingByOrderDate(Date date);

}
