package com.bianyiit.dao;

import com.bianyiit.pojo.Member;

public interface MemberDao {
    Member findMenberByTel(String telephone);

    void addMember(Member member);

    Integer getMemberByMonth(String s);

    Integer getCountThisDay(String today);

    Integer gettotalCount();

    Integer getCountThisWeek(String thisWeekMonday);
}
