package com.example.nxttrendz1.service;

import com.example.nxttrendz1.repository.ReviewJpaRepository;
import com.example.nxttrendz1.repository.ReviewRepository;
import com.example.nxttrendz1.repository.ProductJpaRepository;
import com.example.nxttrendz1.model.Review;
import com.example.nxttrendz1.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewJpaService implements ReviewRepository {
    @Autowired
    private ReviewJpaRepository reviewJpaRepository;
    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Override
    public ArrayList<Review> getReviews() {
        List<Review> list = reviewJpaRepository.findAll();
        ArrayList<Review> reviewList = new ArrayList<>(list);
        return reviewList;
    }

    @Override
    public Review getReviewById(int reviewId) {
        try {
            Review review = reviewJpaRepository.findById(reviewId).get();
            return review;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Review addReview(Review review) {
        Product product = review.getProduct();
        int productId = product.getProductId();

        Product newProduct = productJpaRepository.findById(productId).get();
        review.setProduct(newProduct);
        reviewJpaRepository.save(review);
        return review;
    }

    @Override
    public Review updateReview(int reviewId, Review review) {
        try {
            Review r = reviewJpaRepository.findById(reviewId).get();
            if (review.getReviewContent() != null) {
                r.setReviewContent(review.getReviewContent());
            }
            if (review.getRating() != 0) {
                r.setRating(review.getRating());
            }
            if (review.getProduct() != null) {
                Product product = review.getProduct();
                int productId = product.getProductId();
                Product newProduct = productJpaRepository.findById(productId).get();
                r.setProduct(newProduct);
            }
            reviewJpaRepository.save(r);
            return r;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Product getProductByReviewId(int reviewId) {
        try {
            Review review = reviewJpaRepository.findById(reviewId).get();
            Product product = review.getProduct();
            int productId = product.getProductId();
            Product newProduct = productJpaRepository.findById(productId).get();
            return newProduct;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteReview(int reviewId) {
        try {
            reviewJpaRepository.deleteById(reviewId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}