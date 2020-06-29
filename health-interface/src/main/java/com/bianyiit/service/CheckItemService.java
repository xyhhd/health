package com.bianyiit.service;

import com.bianyiit.entity.PageResult;
import com.bianyiit.entity.QueryPageBean;
import com.bianyiit.entity.Result;
import com.bianyiit.pojo.CheckItem;

public interface CheckItemService {
    public Result addCheckItem(CheckItem checkItem);

    public PageResult fingPage(QueryPageBean queryPageBean);

    public Result deleteCheckitem(Integer id);

    public Result findCheckitemById(Integer id);

    public Result edit(CheckItem checkItem);

    public Result findAll();
}
