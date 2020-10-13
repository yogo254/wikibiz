package com.wikibiz.productcatalogue.repo;

import java.util.List;

import com.wikibiz.productcatalogue.domain.ProductTag;

import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductTagRepo extends PagingAndSortingRepository<ProductTag, String> {

    List<ProductTag> findAllByProductId(String productId);

    List<ProductTag> findAllByTagContainsIgnoreCase(String tag);

    List<ProductTag> findAllByTag(String tag);

}
