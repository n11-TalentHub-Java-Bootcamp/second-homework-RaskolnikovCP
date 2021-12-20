package com.ozansaribal.n11_bootcamp_week2_trial.Service.EntityService;

import com.ozansaribal.n11_bootcamp_week2_trial.Dao.ProductCommentDao;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.Category;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.ProductComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCommentEntityService {

    @Autowired
    private ProductCommentDao productCommentDao;

    public List<ProductComment> findAll() {

        return (List<ProductComment>) productCommentDao.findAll();

    }

    public List<ProductComment> findCommentByMemberId(Long member_id){

        return productCommentDao.findCommentByMemberId(member_id);

        /*
        ProductComment productComment = null;
        if (optionalCategory.isPresent()){
            productComment = optionalCategory.get();
        }

        return productComment;
        */

        //return productCommentDao.findCommentByMemberId(memberId);

    }

    public List<ProductComment> findCommentByProductId(Long product_id){

        return productCommentDao.findCommentByProductId(product_id);

        /*
        ProductComment productComment = null;
        if (optionalCategory.isPresent()){
            productComment = optionalCategory.get();
        }

        return productComment;
        */

        //return productCommentDao.findCommentByMemberId(memberId);

    }

    public ProductComment save(ProductComment productComment){

        return productCommentDao.save(productComment);

    }

    public void deleteById(Long id){

        productCommentDao.deleteById(id);

    }

}
