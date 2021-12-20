package com.ozansaribal.n11_bootcamp_week2_trial.Controller;

import com.ozansaribal.n11_bootcamp_week2_trial.Converter.CategoryConverter;
import com.ozansaribal.n11_bootcamp_week2_trial.Converter.MemberConverter;
import com.ozansaribal.n11_bootcamp_week2_trial.Converter.ProductCommentConverter;
import com.ozansaribal.n11_bootcamp_week2_trial.Dto.CategoryDto;
import com.ozansaribal.n11_bootcamp_week2_trial.Dto.MemberDto;
import com.ozansaribal.n11_bootcamp_week2_trial.Dto.ProductCommentDto;
import com.ozansaribal.n11_bootcamp_week2_trial.Dto.ProductDetailsDto;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.Category;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.Member;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.ProductComment;
import com.ozansaribal.n11_bootcamp_week2_trial.Exception.CommentNotFoundByMember;
import com.ozansaribal.n11_bootcamp_week2_trial.Exception.CommentNotFoundByProduct;
import com.ozansaribal.n11_bootcamp_week2_trial.Service.EntityService.ProductCommentEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private ProductCommentEntityService productCommentEntityService;

    @GetMapping("/{member_id}")
    public List<ProductCommentDto> findAllCommentsByMemberList(Long member_id){

        List<ProductComment> productCommentList = productCommentEntityService.findCommentByMemberId(member_id);

        for(ProductComment productComment : productCommentList){
            if(productComment.getComment() == null){
                throw new CommentNotFoundByMember("No comment has written by this member: "
                                                  + member_id + ". Check the credentials, please!");
            }
        }

        List<ProductCommentDto> productCommentDtoList = ProductCommentConverter.INSTANCE.convertAllProductCommentListToProductCommentDtoList(productCommentList);

        return productCommentDtoList;

    }

    @GetMapping("/{product_id}")
    public List<ProductCommentDto> findAllCommentsByProductList(Long product_id){

        List<ProductComment> productCommentList = productCommentEntityService.findCommentByProductId(product_id);

        for(ProductComment productComment : productCommentList){
            if(productComment.getComment() == null){
                throw new CommentNotFoundByProduct("No comment has written for this product: "
                                                   + product_id + ". Check the credentials, please!");
            }
        }

        List<ProductCommentDto> productCommentDtoList = ProductCommentConverter.INSTANCE.convertAllProductCommentListToProductCommentDtoList(productCommentList);

        return productCommentDtoList;

    }

    // here, I've used save method for new comment records

    @PostMapping("")
    public ResponseEntity<Object> save(@RequestBody ProductCommentDto productCommentDto){

        ProductComment productComment = ProductCommentConverter.INSTANCE.convertProductCommentDtoToProductComment(productCommentDto);

        productComment = productCommentEntityService.save(productComment);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productComment.getId())
                .toUri();

        return ResponseEntity. created(uri).build();
    }

    @DeleteMapping("/{comment_id}")
    public void delete(Long comment_id){

        productCommentEntityService.deleteById(comment_id);

    }



}
