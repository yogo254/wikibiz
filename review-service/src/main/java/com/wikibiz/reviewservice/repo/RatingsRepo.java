package com.wikibiz.reviewservice.repo;

import java.util.List;

import com.wikibiz.reviewservice.model.Rating;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingsRepo extends MongoRepository<Rating, String> {

    List<Rating> findAllByProductId(String productId);

}
