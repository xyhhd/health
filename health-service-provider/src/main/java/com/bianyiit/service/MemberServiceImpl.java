package com.bianyiit.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bianyiit.dao.MemberDao;
import com.bianyiit.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService{
    @Autowired
    MemberDao memberDao;

    @Override
    public Member findMemberByTel(String telephone) {
        Member member = memberDao.findMenberByTel(telephone);
        return member;
    }

    @Override
    public void addMember(Member member) {
        memberDao.addMember(member);

    }

    @Override
    public List<Integer> getMemberByMonth(List<String> s) {
        List<Integer> list = new ArrayList<>();
        for (String date : s) {
            date = date+"-31";
            Integer integer = memberDao.getMemberByMonth(date);
            list.add(integer);
        }


        return list;
    }
}
