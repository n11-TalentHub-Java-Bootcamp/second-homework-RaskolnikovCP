package com.ozansaribal.n11_bootcamp_week2_trial.Controller;

import com.ozansaribal.n11_bootcamp_week2_trial.Converter.CategoryConverter;
import com.ozansaribal.n11_bootcamp_week2_trial.Converter.ProductConverter;
import com.ozansaribal.n11_bootcamp_week2_trial.Dto.CategoryDto;
import com.ozansaribal.n11_bootcamp_week2_trial.Dto.ProductDetailsDto;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.Category;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.Product;
import com.ozansaribal.n11_bootcamp_week2_trial.Service.EntityService.CategoryEntityService;
import com.ozansaribal.n11_bootcamp_week2_trial.Service.EntityService.ProductEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryEntityService categoryEntityService;

    @Autowired
    private ProductEntityService productEntityService;

    @GetMapping("")
    public List<CategoryDto> findAll(){

        List<Category> categoryList = categoryEntityService.findAll();

        List<CategoryDto> categoryDtoList = CategoryConverter.INSTANCE.convertAllCategoryListToCategoryDtoList(categoryList);

        return categoryDtoList;
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id){

        Category category = categoryEntityService.findById(id);

        return category;
    }

    // input value must be dto

    @PostMapping("")
    public ResponseEntity<Object> save(@RequestBody CategoryDto categoryDto){

        Category category = CategoryConverter.INSTANCE.convertCategoryDtoToCategory(categoryDto);

        //we need to do checking

        if (category.getTopCategory() != null && category.getTopCategory().getId() == null){
            category.setTopCategory(null);
        }

        category = categoryEntityService.save(category);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    // input value must be dto

    @PutMapping("")
    public CategoryDto update(@RequestBody CategoryDto categoryDto){

        Category category = CategoryConverter.INSTANCE.convertCategoryDtoToCategory(categoryDto);

        // we need to do checking

        if (category.getTopCategory() != null && category.getTopCategory().getId() == null){
            category.setTopCategory(null);
        }

        category = categoryEntityService.save(category);

        CategoryDto categoryDtoResult = CategoryConverter.INSTANCE.convertCategoryToCategoryDto(category);

        return categoryDtoResult;
    }

    @DeleteMapping("/{id}")
    public void delete(Long id){
        categoryEntityService.deleteById(id);
    }

    // localhost:8080/api/categories/{id}/products
    @GetMapping("/{id}/products")
    public List<ProductDetailsDto> findAllProductByCategoryId(@PathVariable Long id){
        List<Product> productList = productEntityService.findAllByCategoryOrderByIdDesc(id);

        List<ProductDetailsDto> productDetailsDtoList = ProductConverter.INSTANCE.convertAllProductListToProductDetailsDtoList(productList);

        return productDetailsDtoList;
    }

}
