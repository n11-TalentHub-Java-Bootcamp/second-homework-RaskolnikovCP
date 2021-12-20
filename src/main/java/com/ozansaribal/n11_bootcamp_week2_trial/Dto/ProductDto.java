package com.ozansaribal.n11_bootcamp_week2_trial.Dto;

import java.math.BigDecimal;
import java.util.Date;

public class ProductDto {

    public ProductDto(Long id, String name, BigDecimal price, Date registerDate, Long categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.registerDate = registerDate;
        this.categoryId = categoryId;
    }

    public ProductDto() {
    }

    private Long id;
    private String name;
    private BigDecimal price;
    private Date registerDate;
    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
