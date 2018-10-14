package com.travelapp.service;


import com.travelapp.model.Comment;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Comment.
 */
public interface CommentService {


    Comment save(Comment commentDTO);

    /**
     * Get all the comments.
     *
     * @return the list of entities
     */
    List<Comment> findAll();


    /**
     * Get the "id" comment.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Comment> findOne(Long id);

    /**
     * Delete the "id" comment.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
