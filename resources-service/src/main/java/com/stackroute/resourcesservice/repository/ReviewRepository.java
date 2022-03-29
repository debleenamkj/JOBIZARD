package com.stackroute.resourcesservice.repository;

import com.stackroute.resourcesservice.domain.Company;
import com.stackroute.resourcesservice.domain.Review;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Company, String> {
    Company findByCompanyName(String companyName);
    @Query(value = "{'companyName': '?0', 'reviews.reviewId': ?1}", fields = "{companyId: 0, 'reviews.$': 1}" )
    List<Review> findReviewByCompanyNameAndReviews_ReviewId(String companyName, int reviewId);
    //@Aggregation("[{ '$match': { companyId: '?0' } }, { $addFields: { reviews: { $concatArrays: [ '$reviews', [?1] ] } } }]")
   // List<Review> groupByCompanyNameAndReviews_ReviewIdAnd(Company company,Review review);
}
