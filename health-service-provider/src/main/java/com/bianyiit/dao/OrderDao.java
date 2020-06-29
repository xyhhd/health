package com.bianyiit.dao;

import com.bianyiit.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    List<Order> findByCondition(Order order);

    void addOrder(Order order);

    Map findById(Integer id);

    Integer getOrdderThisDay(String today);

    Integer getOrdderThisWeek(String thisWeekMonday);

    Integer getVisitsThisDay(String today);

    Integer getVisitsThisWeek(String thisWeekMonday);

    List<Map<String, Object>> findHotSetMeal();

}
