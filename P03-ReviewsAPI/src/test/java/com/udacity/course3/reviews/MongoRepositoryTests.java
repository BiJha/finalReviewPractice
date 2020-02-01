package com.udacity.course3.reviews;

import com.udacity.course3.reviews.model.CommentMongo;
import com.udacity.course3.reviews.model.ReviewMongo;
import com.udacity.course3.reviews.repository.ReviewMongoRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest


public class MongoRepositoryTests {
    @Autowired
    ReviewMongoRepository reviewMongoRepository;

    @Autowired
    MongoTemplate mongoTemplate;


    @Test
    public void commentReview() {
        CommentMongo comment = new CommentMongo(1, "Good for me as well.", "1/30/2020", "Steven");
        List<CommentMongo> comments = new ArrayList<CommentMongo>();
        comments.add(comment);
        ReviewMongo review = new ReviewMongo(1, 5, "Good Product", "1/30/2020", "Mark");
        review = mongoTemplate.save(review, "reviews");

        if (reviewMongoRepository.existsById(review.getId())) {
            ReviewMongo result = reviewMongoRepository.findById(review.getId()).get();
            Assert.assertTrue(review.getComments().get(0).getComment_user_name().equals(result.getComments().get(0).getComment_user_name()));
            Assert.assertTrue(review.getReview().equals(result.getReview()));
        } else {
            Assert.fail(" If given Id does not exist this will return");
        }
    }

}
