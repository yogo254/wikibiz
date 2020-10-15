package com.wikibiz.reviewservice.controler;

import java.util.List;

import com.wikibiz.reviewservice.model.Review;
import com.wikibiz.reviewservice.repo.ReviewRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/review")
public class ReviewControler {
    @Autowired
    private ReviewRepo reviewRepo;

    @GetMapping("/product/{id}")
    public List<Review> getAllProductReviews(@PathVariable("id") String id) {
        return reviewRepo.findAllByProductId(id);
    }

    @PostMapping
    public String addProductReview(@RequestBody Review review) {
        reviewRepo.save(review);
        return "Review addded successfuly";

    }

}
