package com.wikibiz.productcatalogue.repo;

import java.util.List;

import com.wikibiz.productcatalogue.domain.ProductDescription;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductDescriptionRepo extends PagingAndSortingRepository<ProductDescription, String> {

    List<ProductDescription> findAllByProductId(String productId);

}
