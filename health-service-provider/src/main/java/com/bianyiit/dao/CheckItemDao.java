package com.bianyiit.dao;

import com.bianyiit.pojo.CheckItem;
import com.github.pagehelper.Page;

import java.util.ArrayList;
import java.util.List;

public interface CheckItemDao {
    public void addCheckItem(CheckItem checkItem);

    public Page<CheckItem> fingPage(String queryString);

    public Long selectCheckItemCount(String queryString);

    public void deleteCheckitem(Integer id);

    public CheckItem findCheckitemById(Integer id);

    public void edit(CheckItem checkItem);

    public Integer fingCountById(Integer id);

    public List<CheckItem> fingAll();

    public List<CheckItem>  findCheckItemsById(Integer id);
}
