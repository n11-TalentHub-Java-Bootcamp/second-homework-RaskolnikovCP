package com.ozansaribal.n11_bootcamp_week2_trial.Dao;

import com.ozansaribal.n11_bootcamp_week2_trial.Entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao extends CrudRepository<Category, Long> {

    List<Category> findAllByTopCategoryIsNullOrderByNameDesc();

    List<Category> findAllByNameEndsWith(String adi);

}
