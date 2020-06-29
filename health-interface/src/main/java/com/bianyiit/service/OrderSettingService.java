package com.bianyiit.service;

import com.bianyiit.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    public void addOrderSetting(List<OrderSetting> list);

    public List<Map> getOrderSettingByMonth(String date);

    public void editNumberByDate(OrderSetting orderSetting);
}
