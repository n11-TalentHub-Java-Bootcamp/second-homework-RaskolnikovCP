package com.ozansaribal.n11_bootcamp_week2_trial.Dao;

import com.ozansaribal.n11_bootcamp_week2_trial.Entity.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDao extends CrudRepository<Member, Long> {

    @Query("select member from Member member where member.nickname = :nickname")
    public List<Member> findByNickname(String nickname);

    @Query("select member from Member member where member.telephone = :telephone")
    public List<Member> findByTelephone(String telephone);

    @Query("delete from Member member where member.nickname = :nickname and member.telephone = :telephone")
    public void deleteByNicknameAndTelephone(String nickname, String telephone);

}
