package com.ozansaribal.n11_bootcamp_week2_trial.Dto;

import java.math.BigDecimal;
import java.util.Date;

public class ProductCommentDto {

    public ProductCommentDto(Long comment_id, String comment_dto, Date comment_date_dto,
                             Long product_id_dto, Long member_id_dto, String nickname ) {
        this.comment_id = comment_id;
        this.comment_dto = comment_dto;
        this.comment_date_dto = comment_date_dto;
        this.product_id_dto = product_id_dto;
        this.member_id_dto = member_id_dto;
        this.nickname = nickname;

    }

    public ProductCommentDto() {
    }

    private Long comment_id;
    private String comment_dto;
    private Date comment_date_dto;
    private Long product_id_dto;
    private Long member_id_dto;
    private String nickname;

    public Long getCommentId() { return comment_id; }

    public void setCommentId(Long comment_id) { this.comment_id = comment_id; }

    public String getCommentDto() { return comment_dto; }

    public void setCommentDto(String comment_dto) {
        this.comment_dto = comment_dto;
    }

    public Date getCommentDateDto() {
        return comment_date_dto;
    }

    public void setCommentDateDto(Date comment_date_dto) {
        this.comment_date_dto = comment_date_dto;
    }

    public Long getProductIdDto() { return product_id_dto; }

    public void setProductIdDto(Long product_id_dto) { this.product_id_dto = product_id_dto; }

    public Long getMemberIdDto() { return member_id_dto; }

    public void setMemberIdDto(Long member_id_dto) { this.member_id_dto = member_id_dto; }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }



}
