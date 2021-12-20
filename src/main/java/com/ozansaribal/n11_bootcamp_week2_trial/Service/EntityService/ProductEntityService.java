package com.ozansaribal.n11_bootcamp_week2_trial.Service.EntityService;

import com.ozansaribal.n11_bootcamp_week2_trial.Dao.ProductDao;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductEntityService {

    @Autowired
    private ProductDao productDao;

    public List<Product> findAll() {

        return (List<Product>) productDao.findAll();

    }

    public Product findById(Long id){
        Optional<Product> optionalProduct = productDao.findById(id);

        Product product = null;
        if (optionalProduct.isPresent()){
            product = optionalProduct.get();
        }

        return product;
    }

    public Product save(Product product){
        product = productDao.save(product);

        return product;
    }

    public void delete(Product product){
        productDao.delete(product);
    }

    public void deleteById(Long id){
        productDao.deleteById(id);
    }

    public long count(){
        return productDao.count();
    }

    public List<Product> findAllByCategoryOrderByIdDesc(Long categoryId){
        return productDao.findAllByCategoryOrderByIdDesc(categoryId);
    }

}
