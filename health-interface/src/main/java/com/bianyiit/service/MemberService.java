package com.bianyiit.service;

import com.bianyiit.pojo.Member;

import java.util.List;

public interface MemberService {
    //根据手机号码查询对象
    Member findMemberByTel(String telephone);

    void addMember(Member member);

    List<Integer> getMemberByMonth(List<String> s);
}
