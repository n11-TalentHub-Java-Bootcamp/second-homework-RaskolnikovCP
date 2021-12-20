package com.ozansaribal.n11_bootcamp_week2_trial.Dao;

import com.ozansaribal.n11_bootcamp_week2_trial.Entity.ProductComment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCommentDao extends CrudRepository<ProductComment, Long> {

    @Query("select comment from ProductComment productComment where memberId = :memberId")
    public List<ProductComment> findCommentByMemberId(Long memberId);

    @Query("select comment from ProductComment productComment where productId = :productId")
    public List<ProductComment> findCommentByProductId(Long productId);


}
