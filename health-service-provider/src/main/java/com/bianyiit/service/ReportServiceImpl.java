package com.bianyiit.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bianyiit.dao.MemberDao;
import com.bianyiit.dao.OrderDao;
import com.bianyiit.dao.SetmealDao;
import com.bianyiit.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = ReportService.class)
@Transactional
public class ReportServiceImpl implements ReportService{
    @Autowired
    OrderDao orderDao;

    @Autowired
    MemberDao memberDao;

    @Override
    public Map<String, Object> getBusinessReportData() throws Exception {
        //获取当前日期
        String today = DateUtils.parseDate2String(DateUtils.getToday());
        //获取本周第一天
        String thisWeekMonday = DateUtils.parseDate2String(DateUtils.getThisWeekMonday());
        //获取本月第一天
        String firstDay4ThisMonth = DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());
        /*会员数据*/
        Integer todayNewMember = memberDao.getCountThisDay(today);
        Integer totalMember = memberDao.gettotalCount();
        Integer thisWeekNewMember = memberDao.getCountThisWeek(thisWeekMonday);
        Integer thisMonthNewMember = memberDao.getCountThisWeek(firstDay4ThisMonth);
        /*预约数据*/
        Integer todayOrderNumber = orderDao.getOrdderThisDay(today);
        Integer thisWeekOrderNumber = orderDao.getOrdderThisWeek(thisWeekMonday);
        Integer thisMonthOrderNumber = orderDao.getOrdderThisWeek(firstDay4ThisMonth);
        Integer todayVisitsNumber = orderDao.getVisitsThisDay(today);
        Integer thisWeekVisitsNumber = orderDao.getVisitsThisWeek(thisWeekMonday);
        Integer thisMonthVisitsNumber = orderDao.getVisitsThisWeek(firstDay4ThisMonth);
        Map<String, Object> map = new HashMap<>();
        map.put("reportDate",today);
        map.put("todayNewMember",todayNewMember);
        map.put("totalMember",totalMember);
        map.put("thisWeekNewMember",thisWeekNewMember);
        map.put("thisMonthNewMember",thisMonthNewMember);
        map.put("todayOrderNumber",todayOrderNumber);
        map.put("thisWeekOrderNumber",thisWeekOrderNumber);
        map.put("thisMonthOrderNumber",thisMonthOrderNumber);
        map.put("todayVisitsNumber",todayVisitsNumber);
        map.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
        map.put("thisMonthVisitsNumber",thisMonthVisitsNumber);
        List<Map<String,Object>> hotSetMeal = orderDao.findHotSetMeal();
        map.put("hotSetmeal",hotSetMeal);
        return map;
    }
}
