package com.ozansaribal.n11_bootcamp_week2_trial.Converter;

import com.ozansaribal.n11_bootcamp_week2_trial.Dto.MemberDto;
import com.ozansaribal.n11_bootcamp_week2_trial.Dto.ProductCommentDto;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.Member;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.ProductComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductCommentConverter {

    ProductCommentConverter INSTANCE = Mappers.getMapper(ProductCommentConverter.class);

    @Mapping(source = "id", target = "comment_id")
    @Mapping(source = "comment", target = "comment_dto")
    @Mapping(source = "comment_date", target = "comment_date_dto")
    @Mapping(source = "product_id", target = "product_id_dto")
    @Mapping(source = "member_id", target = "member_id_dto")
    List <ProductCommentDto> convertAllProductCommentListToProductCommentDtoList(List<ProductComment> productCommentList);

    @Mapping(source = "comment_id", target = "id")
    @Mapping(source = "comment_dto", target = "comment")
    @Mapping(source = "comment_date_dto", target = "comment_date")
    @Mapping(source = "product_id_dto", target = "product_id")
    @Mapping(source = "member_id_dto", target = "member_id")
    ProductComment convertProductCommentDtoToProductComment (ProductCommentDto productCommentDto);

}
