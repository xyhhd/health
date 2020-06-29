package com.bianyiit.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bianyiit.constant.MessageConstant;
import com.bianyiit.dao.SetmealDao;
import com.bianyiit.entity.PageResult;
import com.bianyiit.entity.QueryPageBean;
import com.bianyiit.entity.Result;
import com.bianyiit.pojo.CheckItem;
import com.bianyiit.pojo.Setmeal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService{
    @Autowired
    SetmealDao setmealDao;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        Long count = setmealDao.countSemeal(queryString);
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page = setmealDao.findPage(queryString);
        return new PageResult(count,page);
    }

    @Override
    public Result addSetmeal(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.addSetmeal(setmeal);
        this.setRelation(setmeal.getId(),checkgroupIds);
        return null;
    }

    @Override
    public Result findById(Integer id) {
        try {
            Setmeal setmeal = setmealDao.findById(id);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }
    @Override
    public Result findCheckGroupIdBySetmeal(Integer id){
        try {
            List<Integer> list = setmealDao.findCheckGroupIdBySetmeal(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @Override
    public Result edit(Setmeal setmeal, Integer[] checkgroupIds) {
        try {
            setmealDao.delCheckGroupIdBySetmeal(setmeal.getId());
            this.setRelation(setmeal.getId(),checkgroupIds);
            setmealDao.edit(setmeal);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    @Override
    public Result delete(Integer id) {
        try {
            setmealDao.delCheckGroupIdBySetmeal(id);
            setmealDao.delete(id);
            return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }

    @Override
    public List<Setmeal> findAll() {
        List<Setmeal> list=setmealDao.findAll();
        return list;
    }

    @Override
    public Setmeal findSetmealById(Integer id) {
        Setmeal setmeal=setmealDao.findSetmealById(id);
        return setmeal;
    }

    @Override
    public List<Map<String, Object>> findSetmealCount() {
        List<Map<String, Object>> list = setmealDao.findSetmealCount();
        return list;
    }


    public void setRelation(Integer setmealId,Integer[] checkgroupIds){
        if(checkgroupIds!=null){
            for (Integer checkitemId : checkgroupIds) {
                setmealDao.add_t_setmeal_checkgroup(setmealId,checkitemId);
            }
        }
    }
}
