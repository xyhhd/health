package com.bianyiit.service;

import com.bianyiit.entity.PageResult;
import com.bianyiit.entity.QueryPageBean;
import com.bianyiit.entity.Result;
import com.bianyiit.pojo.CheckGroup;
import com.bianyiit.pojo.CheckItem;

import java.util.ArrayList;

public interface CheckGroupService {
    public Result addCheckGroup(Integer[] checkitemIds,CheckGroup checkGroup);

    public PageResult fingPage(QueryPageBean queryPageBean);

    public Result deleteCheckGroup(Integer id);

    public Result findCheckGroupById(Integer id);

    public Result edit(Integer[] checkitemIds,CheckGroup checkGroup);

    public Result findCheckItemIdsByCheckGroupId(Integer id);

    public Result findAll();
}
