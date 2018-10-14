package com.travelapp.controller;
import com.travelapp.controller.util.HeaderUtil;
import com.travelapp.exception.BadRequestException;
import com.travelapp.exception.ResourceNotFoundException;
import com.travelapp.model.Comment;
import com.travelapp.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Comment.
 */
@RestController
@RequestMapping("/api")
public class CommentResource {

    private final Logger log = LoggerFactory.getLogger(CommentResource.class);

    private static final String ENTITY_NAME = "comment";

    private final CommentService commentService;
    @Autowired
    public CommentResource(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(@Valid @RequestBody Comment commentDTO) throws URISyntaxException {
        log.debug("REST request to save Comment : {}", commentDTO);
        if (commentDTO.getId() != null) {
            throw new BadRequestException("A new comment cannot already have an ID");
        }
        Comment result = commentService.save(commentDTO);
        return ResponseEntity.created(new URI("/api/comments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    @PutMapping("/comments")
    public ResponseEntity<Comment> updateComment(@Valid @RequestBody Comment commentDTO) throws URISyntaxException {
        log.debug("REST request to update Comment : {}", commentDTO);
        if (commentDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        Comment result = commentService.save(commentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, commentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /comments : get all the comments.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of comments in body
     */
    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        log.debug("REST request to get all Comments");
        return commentService.findAll();
    }

    /**
     * GET  /comments/:id : get the "id" comment.
     *
     * @param id the id of the commentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the commentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Long id) {
        log.debug("REST request to get Comment : {}", id);
        Optional<Comment> commentDTO = commentService.findOne(id);
        if (commentDTO==null){
            throw new ResourceNotFoundException(ENTITY_NAME,"id",id);
        }
        return ResponseEntity.ok(commentDTO.get());
    }

    /**
     * DELETE  /comments/:id : delete the "id" comment.
     *
     * @param id the id of the commentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        log.debug("REST request to delete Comment : {}", id);
        commentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
