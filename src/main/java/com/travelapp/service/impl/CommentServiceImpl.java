package com.travelapp.service.impl;

import com.travelapp.model.Comment;
import com.travelapp.repository.CommentRepository;
import com.travelapp.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Comment.
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;

@Autowired
    public CommentServiceImpl(CommentRepository commentRepository ) {
        this.commentRepository = commentRepository;
    }

    /**
     * Save a comment.
     *
     * @param commentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Comment save(Comment commentDTO) {
        log.debug("Request to save Comment : {}", commentDTO);


        commentDTO = commentRepository.save(commentDTO);
        return commentDTO;
    }

    /**
     * Get all the comments.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        log.debug("Request to get all Comments");
        return commentRepository.findAll().stream()

            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one comment by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findOne(Long id) {
        log.debug("Request to get Comment : {}", id);
        return commentRepository.findById(id)
            ;
    }

    /**
     * Delete the comment by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Comment : {}", id);
        commentRepository.deleteById(id);
    }
}
