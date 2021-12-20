package com.ozansaribal.n11_bootcamp_week2_trial.Converter;

import com.ozansaribal.n11_bootcamp_week2_trial.Dto.ProductDetailsDto;
import com.ozansaribal.n11_bootcamp_week2_trial.Dto.ProductDto;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductConverter {

    ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);

    @Mapping(source = "categoryId", target = "category.id")
    Product convertProductDtoToProduct(ProductDto productDto);

    @Mapping(target = "categoryId", source = "category.id")
    ProductDto convertProductToProductDto(Product product);

    @Mapping(source = "price", target = "productPrice")
    @Mapping(source = "name", target = "productName")
    @Mapping(source = "category.name", target = "categoryName")
    ProductDetailsDto convertProductToProductDetailsDto(Product product);

    @Mapping(source = "price", target = "productPrice")
    @Mapping(source = "name", target = "productName")
    @Mapping(source = "category.name", target = "categoryName")
    List<ProductDetailsDto> convertAllProductListToProductDetailsDtoList(List<Product> productList);

}
