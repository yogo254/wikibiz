package com.wikibiz.reviewservice.repo;

import java.util.List;

import com.wikibiz.reviewservice.model.Review;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepo extends MongoRepository<Review, String> {

    List<Review> findAllByProductId(String productId);

}
