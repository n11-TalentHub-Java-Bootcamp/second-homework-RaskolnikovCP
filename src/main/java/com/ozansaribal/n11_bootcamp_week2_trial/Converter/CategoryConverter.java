package com.ozansaribal.n11_bootcamp_week2_trial.Converter;

import com.ozansaribal.n11_bootcamp_week2_trial.Dto.CategoryDto;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryConverter {

    CategoryConverter INSTANCE = Mappers.getMapper(CategoryConverter.class);

    @Mapping(target = "topCategoryId", source = "topCategory.id")
    CategoryDto convertCategoryToCategoryDto(Category category);

    @Mapping(target = "topCategoryId", source = "topCategory.id")
    List<CategoryDto> convertAllCategoryListToCategoryDtoList(List<Category> categoryList);

//    @Mapping(target = "ustKategori.id", source = "ustKategoriId", expression = "java(null))
//    @Mapping(target = "ustKategori.id", source = "ustKategoriId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)

    //    @Mapping(target = "ustKategori.id", expression = "java(kategoriDto.getUstKategoriId() == null ? null : " +
//            "kategoriDto.getUstKategoriId())")
    Category convertCategoryDtoToCategory(CategoryDto categoryDto);

}
