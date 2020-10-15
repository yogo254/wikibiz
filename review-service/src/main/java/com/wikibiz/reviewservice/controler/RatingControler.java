package com.wikibiz.reviewservice.controler;

import java.util.IntSummaryStatistics;

import com.wikibiz.reviewservice.model.Rating;
import com.wikibiz.reviewservice.repo.RatingsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/rating")
public class RatingControler {
    @Autowired
    private RatingsRepo ratingsRepo;

    @GetMapping("/product/{id}")
    public IntSummaryStatistics getProductRating(@PathVariable("id") String id) {
        return ratingsRepo.findAllByProductId(id).stream().mapToInt(Rating::getRating).summaryStatistics();
    }

    @PostMapping
    public String addRating(@RequestBody Rating rating) {
        if (rating.getRating() > 5)
            rating.setRating(5);
        if (rating.getRating() < 1)
            rating.setRating(1);

        ratingsRepo.save(rating);
        return "rating added successfuly";
    }

}
