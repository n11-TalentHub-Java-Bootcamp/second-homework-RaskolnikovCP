package com.ozansaribal.n11_bootcamp_week2_trial.Dto;

import java.util.Date;

public class MemberDto {

    public MemberDto(Long member_id, String member_name, String member_email, String member_phone_number) {
        this.member_id = member_id;
        this.member_name = member_name;
        this.member_email = member_email;
        this.member_phone_number = member_phone_number;
    }

    public MemberDto() {
    }

    private Long member_id;
    private String member_name;
    private String member_email;
    private String member_phone_number;

    public Long getMemberId() {
        return member_id;
    }

    public void setMemberId(Long member_id) {
        this.member_id = member_id;
    }

    public String getMemberName() {
        return member_name;
    }

    public void setMemberName(String member_name) {
        this.member_name = member_name;
    }

    public String getEmail() {
        return member_email;
    }

    public void setEmail(String member_email) {
        this.member_email = member_email;
    }

    public String getPhoneNumber() {
        return member_phone_number;
    }

    public void setPhoneNumber(String member_phone_number) {
        this.member_phone_number = member_phone_number;
    }

}
