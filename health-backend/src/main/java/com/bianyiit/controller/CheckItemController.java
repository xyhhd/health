package com.bianyiit.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bianyiit.entity.PageResult;
import com.bianyiit.entity.QueryPageBean;
import com.bianyiit.entity.Result;
import com.bianyiit.pojo.CheckItem;
import com.bianyiit.service.CheckItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference
    CheckItemService checkItemService;

/*添加检查项*/
    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('CHECKITEM_ADD')")
    public Result addCheckitem(@RequestBody CheckItem checkItem){//@RequestBody把前台传来的json数据  转换成Javabean对象
        Result result = checkItemService.addCheckItem(checkItem);
        return result;
    }
/*进行分页查询*/
    @RequestMapping("/findPage")
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){//@RequestBody把前台传来的json数据  转换成Javabean对象

        PageResult pageResult = checkItemService.fingPage(queryPageBean);
        return pageResult;
    }
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")
    public Result deleteCheckitem(Integer id){//@RequestBody把前台传来的json数据  转换成Javabean对象
        System.out.println(id);
        Result result = checkItemService.deleteCheckitem(id);
        return result;
    }
    @RequestMapping("/findById")
    public Result findCheckitemById(Integer id){//@RequestBody把前台传来的json数据  转换成Javabean对象
        Result result = checkItemService.findCheckitemById(id);
        return result;
    }
    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('CHECKITEM_EDIT')")
    public Result edit(@RequestBody CheckItem checkItem){//@RequestBody把前台传来的json数据  转换成Javabean对象
        Result result = checkItemService.edit(checkItem);
        return result;
    }

    @RequestMapping("/findAll")
    public Result findAll(){//@RequestBody把前台传来的json数据  转换成Javabean对象

        Result Result = checkItemService.findAll();
        return Result;
    }

}
