package com.bianyiit.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bianyiit.constant.MessageConstant;
import com.bianyiit.dao.CheckGroupDao;
import com.bianyiit.entity.PageResult;
import com.bianyiit.entity.QueryPageBean;
import com.bianyiit.entity.Result;
import com.bianyiit.pojo.CheckGroup;
import com.bianyiit.pojo.CheckItem;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckGroupService.class) //实现这个接口
@Transactional //使用事务
public class CheckGroupServiceImpl implements CheckGroupService{
    @Autowired
    CheckGroupDao checkGroupDao;

    @Override
    public Result addCheckGroup(Integer[] checkitemIds,CheckGroup checkGroup) {
        try {
            checkGroupDao.addCheckGroup(checkGroup);
            int checkGroupId = checkGroup.getId();
            this.setRelation(checkGroupId,checkitemIds);
            return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }

    }

    @Override
    public PageResult fingPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        Long count = checkGroupDao.selectCheckGroupCount(queryPageBean.getQueryString());
        Page<CheckGroup> pages;
        if(count<(currentPage-1)*pageSize&&queryPageBean.getQueryString()!=null&&queryPageBean.getQueryString().length()>0){
            PageHelper.startPage(1,pageSize);
            pages= checkGroupDao.fingPage(queryPageBean.getQueryString());
        }else {
            PageHelper.startPage(currentPage,pageSize);
            pages= checkGroupDao.fingPage(queryPageBean.getQueryString());
        }

        return new PageResult(count,pages);

    }

    @Override
    public Result deleteCheckGroup(Integer id) {
        try {
            Integer count=checkGroupDao.fingCountById(id);
            if(count==0){
                checkGroupDao.deleteCheckGroup(id);
                return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
            }else {
                checkGroupDao.delCheckItemIdsByCheckGroupId(id);
                checkGroupDao.deleteCheckGroup(id);
                return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }

    @Override
    public Result findCheckGroupById(Integer id) {
        try {
            CheckItem checkItem = checkGroupDao.findCheckGroupById(id);
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @Override
    public Result edit(Integer[] checkitemIds,CheckGroup checkGroup) {
        try {
            checkGroupDao.edit(checkGroup);
            checkGroupDao.delCheckItemIdsByCheckGroupId(checkGroup.getId());
            this.setRelation(checkGroup.getId(),checkitemIds);
            return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    @Override
    public Result findCheckItemIdsByCheckGroupId(Integer id) {
        try {
            List<Integer> list=checkGroupDao.findCheckItemIdsByCheckGroupId(id);
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @Override
    public Result findAll() {
        try {
            List<CheckGroup> list=checkGroupDao.findAll();
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    public void setRelation(Integer checkGroupId,Integer[] checkitemIds){
        if(checkitemIds!=null){
            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.add_checkgroup_checkitem(checkGroupId,checkitemId);
            }
        }
    }
}
