package com.ozansaribal.n11_bootcamp_week2_trial.Service.EntityService;

import com.ozansaribal.n11_bootcamp_week2_trial.Dao.MemberDao;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberEntityService {

    @Autowired
    private MemberDao memberDao;

    public List<Member> findAll() {

        return (List<Member>) memberDao.findAll();

    }

    public Member findById(Long id){
        Optional<Member> optionalCategory = memberDao.findById(id);

        Member member = null;
        if (optionalCategory.isPresent()){
            member = optionalCategory.get();
        }

        return member;
    }

    public List<Member> findByNickname(String nickname){

        return memberDao.findByNickname(nickname);
/*
        Optional<Member> optionalMember = memberDao.findByNickname(nickname);

        Member member = null;

        if (optionalMember.isPresent()){
            member = optionalMember.get();
        }

        return member;
*/
    }

    public List<Member> findByTelephone(String telephone){

        return memberDao.findByTelephone(telephone);

        /*
        Optional<Member> optionalMember = memberDao.findByTelephone(telephone);

        Member member = null;

        if(optionalMember.isPresent()){
            member = optionalMember.get();
        }

        return member;
*/
    }

    public Member save(Member member){

        member = memberDao.save(member);

        return member;
    }

    public void deleteByNicknameAndTelephone(String nickname, String telephone){

        memberDao.deleteByNicknameAndTelephone(nickname, telephone);

    }

}
