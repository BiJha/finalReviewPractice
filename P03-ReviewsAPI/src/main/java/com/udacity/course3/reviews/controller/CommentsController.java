package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.CommentMongo;
import com.udacity.course3.reviews.model.ReviewMongo;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewMongoRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    // TODO: Wire needed JPA repositories here

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewMongoRepository reviewMongoRepository;

    /**
     * Creates a comment for a review.
     * <p>
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") Integer reviewId,
                                                    @RequestBody Comment comment) {
        if (reviewRepository.existsById(reviewId)) {
//            comment.setReview(reviewRepository.findById(reviewId).get());
//            return new ResponseEntity<Comment>(commentRepository.save(comment), HttpStatus.OK); // This will save a
//            comment

            commentRepository.save(comment);
            ReviewMongo reviewMongo = null;
            if (reviewMongoRepository.existsById(reviewId)) {
                reviewMongo = reviewMongoRepository.findById(reviewId).get();
                List<CommentMongo> comments = reviewMongo.getComments();
                if (comments == null) {
                    comments = new ArrayList<CommentMongo>();
                }
                CommentMongo commentMongo = new CommentMongo(comment.getId(), comment.getComment_content(),
                        comment.getComment_date(), comment.getComment_user_name());
                comments.add(commentMongo);
                reviewMongo.setComments(comments);
            }
            return new ResponseEntity<ReviewMongo>(reviewMongoRepository.save(reviewMongo), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("This review ID does not exist!", HttpStatus.NOT_FOUND);
        }

    }

    /**
     * List comments for a review.
     * <p>
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<?> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        if (reviewRepository.existsById(reviewId)) {
            Optional<ReviewMongo> optionalReviewMongo = reviewMongoRepository.findById(reviewId);
            if (optionalReviewMongo.isPresent()) {
                List<CommentMongo> commentMongoList = optionalReviewMongo.get().getComments();

                if (commentMongoList == null || commentMongoList.size() == 0) {
                    return new ResponseEntity<String>("This reviewId does not have any comment!", HttpStatus.NOT_FOUND);
                } else {
                    return new ResponseEntity<List<CommentMongo>>(commentMongoList, HttpStatus.OK); // This will
                    // return the list of comments.
                }

            } else {
                return new ResponseEntity<String>("This reviewId does not exist!", HttpStatus.NOT_FOUND);
            }
//            List<Comment> commentList = reviewMongoRepository.findById(reviewId).getComments();
//             List<Comment> commentList = commentRepository.findAllByReviewId(reviewId);

        } else {
            return new ResponseEntity<String>("This reviewId does not exist!", HttpStatus.NOT_FOUND);
        }


    }
}