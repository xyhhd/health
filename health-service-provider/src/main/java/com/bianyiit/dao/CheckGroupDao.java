package com.bianyiit.dao;

import com.bianyiit.pojo.CheckGroup;
import com.bianyiit.pojo.CheckItem;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckGroupDao {
    public void addCheckGroup(CheckGroup checkGroup);

    public Page<CheckGroup> fingPage(String queryString);

    public Long selectCheckGroupCount(String queryString);

    public void deleteCheckGroup(Integer id);

    public CheckItem findCheckGroupById(Integer id);

    public void edit(CheckGroup checkGroup);

    public Integer fingCountById(Integer id);

    public void add_checkgroup_checkitem(@Param("param1") Integer id, @Param("param2")Integer checkitemId);

    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    public void delCheckItemIdsByCheckGroupId(Integer id);

    public List<CheckGroup> findAll();

    public List<CheckGroup> findGroupsById(Integer id);
}
