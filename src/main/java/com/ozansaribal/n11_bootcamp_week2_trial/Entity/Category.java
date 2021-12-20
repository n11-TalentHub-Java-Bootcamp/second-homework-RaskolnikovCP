package com.ozansaribal.n11_bootcamp_week2_trial.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {

    public Category(Long id, String name, Long refraction, Category topCategory) {
        this.id = id;
        this.name = name;
        this.refraction = refraction;
        this.topCategory = topCategory;
    }

    public Category() {
    }

    @SequenceGenerator(name = "generator", sequenceName = "CATEGORY_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "REFRACTION")
    private Long refraction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TOP_CATEGORY")
    private Category topCategory;

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

    public Long getRefraction() {
        return refraction;
    }

    public void setRefraction(Long refraction) {
        this.refraction = refraction;
    }

    public Category getTopCategory() {
        return topCategory;
    }

    public void setTopCategory(Category topCategory) {
        this.topCategory = topCategory;
    }

    @Override
    public String toString() {
        return id == null ? "" : id.toString();
    }
/*
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", refraction=" + refraction +
                ", topCategory=" + topCategory +
                '}';
    }*/
}
