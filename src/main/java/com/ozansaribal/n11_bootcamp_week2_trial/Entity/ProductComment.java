package com.ozansaribal.n11_bootcamp_week2_trial.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PRODUCT_COMMENT")
public class ProductComment implements Serializable {

    public ProductComment(Long id, String comment, Date comment_date, Long product_id, Long member_id) {
        this.id = id;
        this.comment = comment;
        this.comment_date = comment_date;
        this.product_id = product_id;
        this.member_id = member_id;
    }

    public ProductComment() {
    }

    @SequenceGenerator(name = "generator", sequenceName = "MEMBER_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "COMMENT", length = 500)
    private String comment;

    @Column(name = "COMMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date comment_date;

    @Column(name = "PRODUCT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PRODUCT", foreignKey = @ForeignKey(name = "FK_PRODUCT_COMMENT_PRODUCT_ID"))
    private Long product_id;

    @Column(name = "MEMBER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MEMBER", foreignKey = @ForeignKey(name = "FK_PRODUCT_COMMENT_MEMBER_ID"))
    private Long member_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getComment_date() {
        return comment_date;
    }

    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }
}
