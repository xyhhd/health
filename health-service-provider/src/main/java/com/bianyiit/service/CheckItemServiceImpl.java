package com.bianyiit.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bianyiit.constant.MessageConstant;
import com.bianyiit.dao.CheckItemDao;
import com.bianyiit.entity.PageResult;
import com.bianyiit.entity.QueryPageBean;
import com.bianyiit.entity.Result;
import com.bianyiit.pojo.CheckItem;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = CheckItemService.class) //实现这个接口
@Transactional //使用事务
public class CheckItemServiceImpl implements CheckItemService{
    @Autowired
    CheckItemDao checkItemDao;

    @Override
    public Result addCheckItem(CheckItem checkItem) {
        try {
            checkItemDao.addCheckItem(checkItem);
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }

    }

    @Override
    public PageResult fingPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        Long count = checkItemDao.selectCheckItemCount(queryPageBean.getQueryString());
        Page<CheckItem> pages;
        if(count<(currentPage-1)*pageSize&&queryPageBean.getQueryString()!=null&&queryPageBean.getQueryString().length()>0){
            PageHelper.startPage(1,pageSize);
            pages= checkItemDao.fingPage(queryPageBean.getQueryString());
        }else {
            PageHelper.startPage(currentPage,pageSize);
            pages= checkItemDao.fingPage(queryPageBean.getQueryString());
        }

        return new PageResult(count,pages);

    }

    @Override
    public Result deleteCheckitem(Integer id) {
        System.out.println(2333);
        try {
            Integer count=checkItemDao.fingCountById(id);
            if(count==0){
                checkItemDao.deleteCheckitem(id);
                return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
            }else {
                return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL+"需要先删除关联关系");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }

    @Override
    public Result findCheckitemById(Integer id) {
        try {
            CheckItem checkItem = checkItemDao.findCheckitemById(id);
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @Override
    public Result edit(CheckItem checkItem) {
        try {
            checkItemDao.edit(checkItem);
            return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }

    @Override
    public Result findAll() {
        try {
            List<CheckItem> list=checkItemDao.fingAll();
            return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }
}
