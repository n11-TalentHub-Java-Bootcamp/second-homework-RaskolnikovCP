package com.ozansaribal.n11_bootcamp_week2_trial.Dto;

public class CategoryDto {

    public CategoryDto(Long id, String name, Long refraction, Long topCategoryId) {
        this.id = id;
        this.name = name;
        this.refraction = refraction;
        this.topCategoryId = topCategoryId;
    }

    public CategoryDto() {
    }

    private Long id;
    private String name;
    private Long refraction;
    private Long topCategoryId;

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

    public Long getTopCategoryId() {
        return topCategoryId;
    }

    public void setTopCategoryId(Long topCategoryId) {
        this.topCategoryId = topCategoryId;
    }



}
