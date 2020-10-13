package com.wikibiz.productcatalogue.repo;

import com.wikibiz.productcatalogue.domain.Product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product, String> {

    Slice<Product> findBySellerId(String sellerId, Pageable pageable);

    Slice<Product> findAllByCategoryAndSubCategory(String category, String subCategory, Pageable pageable);

}
