package org.brinka.brinkaapi.domain.repository;

import org.brinka.brinkaapi.domain.model.Review;

import java.util.List;

public interface ReviewRepository {
    List<Review> getReviewsByProductId(Integer productId);
}
