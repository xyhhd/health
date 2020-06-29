package com.bianyiit.service;

import com.bianyiit.entity.Result;

import java.text.ParseException;
import java.util.Map;

public interface OrderService {
    public Result addOrder(Map map) throws Exception;

    public Map findById(Integer id) throws ParseException, Exception;
}
