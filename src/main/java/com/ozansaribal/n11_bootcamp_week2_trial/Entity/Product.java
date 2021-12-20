package com.ozansaribal.n11_bootcamp_week2_trial.Entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PRODUCT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "category"})
@JsonFilter("ProductFilter")
public class Product implements Serializable {

    public Product() {
    }

    public Product(Long id, String name, BigDecimal price, Date registerDate, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.registerDate = registerDate;
        this.category = category;
    }

    @SequenceGenerator(name = "generator", sequenceName = "PRODUCT_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(length = 50, name = "NAME")
    private String name;

    @Column(name = "PRICE" , precision = 19, scale = 2)
    private BigDecimal price;

    @Column(name = "REGISTER_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CATEGORY",
            foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY_ID")
    )
    private Category category;


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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return id == null ? "" : id.toString();
    }

}
