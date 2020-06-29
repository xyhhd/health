package com.bianyiit.service;


import com.bianyiit.entity.PageResult;
import com.bianyiit.entity.QueryPageBean;
import com.bianyiit.entity.Result;
import com.bianyiit.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {

    public PageResult findPage(QueryPageBean queryPageBean);

    public Result addSetmeal(Setmeal setmeal,Integer[] checkgroupIds);

    public Result findById(Integer id);

    public Result findCheckGroupIdBySetmeal(Integer id);

    public Result edit(Setmeal setmeal, Integer[] checkgroupIds);

    public Result delete(Integer id);

    public List<Setmeal> findAll();

    public Setmeal findSetmealById(Integer id);

    List<Map<String, Object>> findSetmealCount();

}
