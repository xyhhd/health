package com.bianyiit.dao;

import com.bianyiit.pojo.Setmeal;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SetmealDao {
    public Page<Setmeal> findPage(String queryString);

    public Long countSemeal(String queryString);

    public void addSetmeal(Setmeal setmeal);

    public void add_t_setmeal_checkgroup(@Param("param1") Integer setmealId, @Param("param2")Integer checkgroupId);

    public Setmeal findById(Integer id);

    public List<Integer> findCheckGroupIdBySetmeal(Integer id);

    public void delCheckGroupIdBySetmeal(Integer id);

    public void edit(Setmeal setmeal);

    public void delete(Integer id);

    public List<Setmeal> findAll();

    public Setmeal findSetmealById(Integer id);

    List<Map<String, Object>> findSetmealCount();
}
