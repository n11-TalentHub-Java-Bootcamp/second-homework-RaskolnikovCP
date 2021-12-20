package com.ozansaribal.n11_bootcamp_week2_trial.Entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MEMBER")
public class Member implements Serializable {

    public Member(Long id, String first_name, String last_name, String email, String phone_number, String nickname) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.nickname = nickname;
    }

    public Member() {
    }

    @SequenceGenerator(name = "generator", sequenceName = "MEMBER_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FIRST_NAME", length = 50)
    private String first_name;

    @Column(name = "LAST_NAME", length = 50)
    private String last_name;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "PHONE_NUMBER", length = 15)
    private String phone_number;

    @Column(name = "NICKNAME", length = 20)
    private String nickname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return id == null ? "" : id.toString();
    }

}
