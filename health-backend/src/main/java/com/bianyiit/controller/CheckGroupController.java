package com.bianyiit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bianyiit.entity.PageResult;
import com.bianyiit.entity.QueryPageBean;
import com.bianyiit.entity.Result;
import com.bianyiit.pojo.CheckGroup;
import com.bianyiit.pojo.CheckItem;
import com.bianyiit.service.CheckGroupService;
import com.bianyiit.service.CheckItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
@ResponseBody
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference
    CheckGroupService checkGroupService;

    /*添加检查项*/
    @RequestMapping("/add")
    public Result addcheckgroup(Integer[] checkitemIds,@RequestBody CheckGroup checkGroup){//@RequestBody把前台传来的json数据  转换成Javabean对象
        Result result = checkGroupService.addCheckGroup(checkitemIds,checkGroup);
        return result;
    }
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){//@RequestBody把前台传来的json数据  转换成Javabean对象

        PageResult pageResult = checkGroupService.fingPage(queryPageBean);
        return pageResult;
    }
    @RequestMapping("/findById")
    public Result findCheckGroupById(Integer id){//@RequestBody把前台传来的json数据  转换成Javabean对象
        Result result = checkGroupService.findCheckGroupById(id);
        return result;
    }
    @RequestMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(Integer id){
        Result result = checkGroupService.findCheckItemIdsByCheckGroupId(id);
        return result;
    }
    @RequestMapping("/edit")
    public Result edit(Integer[] checkitemIds,@RequestBody CheckGroup checkGroup){//@RequestBody把前台传来的json数据  转换成Javabean对象

        Result Result = checkGroupService.edit(checkitemIds,checkGroup);
        return Result;
    }
    @RequestMapping("/delete")
    public Result deleteCheckGroup(Integer id){//@RequestBody把前台传来的json数据  转换成Javabean对象
        System.out.println(id);
        Result result = checkGroupService.deleteCheckGroup(id);
        return result;
    }
    @RequestMapping("/findAll")
    public Result findAll(){//@RequestBody把前台传来的json数据  转换成Javabean对象
        Result Result = checkGroupService.findAll();
        return Result;
    }
}
