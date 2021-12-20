package com.ozansaribal.n11_bootcamp_week2_trial.Controller;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ozansaribal.n11_bootcamp_week2_trial.Converter.ProductConverter;
import com.ozansaribal.n11_bootcamp_week2_trial.Dto.ProductDetailsDto;
import com.ozansaribal.n11_bootcamp_week2_trial.Dto.ProductDto;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.Product;
import com.ozansaribal.n11_bootcamp_week2_trial.Exception.ProductNotFoundException;
import com.ozansaribal.n11_bootcamp_week2_trial.Service.EntityService.CategoryEntityService;
import com.ozansaribal.n11_bootcamp_week2_trial.Service.EntityService.ProductEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductEntityService productEntityService;

    @Autowired
    private CategoryEntityService categoryEntityService;

    @GetMapping("/products")
    public MappingJacksonValue findAllProductList() {

        List<Product> productList = productEntityService.findAll();

        String filterName = "ProductFilter";

        SimpleFilterProvider filters = getProductFilterProvider(filterName);

        MappingJacksonValue mapping = new MappingJacksonValue(productList);

        mapping.setFilters(filters);

        return mapping;
    }



    @GetMapping("/{id}")
    public MappingJacksonValue findProductById(@PathVariable Long id){

        Product product = productEntityService.findById(id);

        if (product == null){
            throw new ProductNotFoundException("Product not found. id: " + id);
        }

        WebMvcLinkBuilder linkToProduct = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass())
                        .findAllProductList()
        );

        ProductDto productDto = ProductConverter.INSTANCE.convertProductToProductDto(product);

        String filterName = "ProductDtoFilter";

        SimpleFilterProvider filters = getProductFilterProvider(filterName);

        EntityModel entityModel = EntityModel.of(productDto);

        entityModel.add(linkToProduct.withRel("all-products"));

        MappingJacksonValue mapping = new MappingJacksonValue(entityModel);

        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/detail/{id}")
    public ProductDetailsDto findProductDtoById(@PathVariable Long id){

        Product product = productEntityService.findById(id);

        if (product == null){
            throw new ProductNotFoundException("Product not found. id: " + id);
        }

        ProductDetailsDto productDetailsDto = ProductConverter.INSTANCE.convertProductToProductDetailsDto(product);

        return productDetailsDto;
    }

    @PostMapping("")
    public ResponseEntity<Object> saveProduct(@RequestBody ProductDto productDto){

        Product product = ProductConverter.INSTANCE.convertProductDtoToProduct(productDto);

        product = productEntityService.save(product);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id){

        productEntityService.deleteById(id);

    }

    @GetMapping("categories/{categoryId}")
    public List<ProductDetailsDto> findAllProductByCategoryId(@PathVariable Long categoryId){

        List<Product> productList = productEntityService.findAllByCategoryOrderByIdDesc(categoryId);

        List<ProductDetailsDto> productDetailsDtoList = ProductConverter.INSTANCE.convertAllProductListToProductDetailsDtoList(productList);

        return productDetailsDtoList;
    }

    private SimpleFilterProvider getProductFilterProvider(String filterName) {
        SimpleBeanPropertyFilter filter = getProductFilter();

        return new SimpleFilterProvider().addFilter(filterName, filter);
    }

    private SimpleBeanPropertyFilter getProductFilter() {
        return SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "price", "registerDate");
    }

}
