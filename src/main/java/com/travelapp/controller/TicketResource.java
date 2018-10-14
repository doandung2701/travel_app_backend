package com.travelapp.controller;

import com.travelapp.controller.util.HeaderUtil;
import com.travelapp.exception.BadRequestException;
import com.travelapp.exception.ResourceNotFoundException;
import com.travelapp.model.Ticket;
import com.travelapp.service.TicketService;
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
 * REST controller for managing Ticket.
 */
@RestController
@RequestMapping("/api")
public class TicketResource {

    private final Logger log = LoggerFactory.getLogger(TicketResource.class);

    private static final String ENTITY_NAME = "ticket";

    private final TicketService ticketService;
    @Autowired
    public TicketResource(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @PostMapping("/tickets")
    public ResponseEntity<Ticket> createTicket(@Valid @RequestBody Ticket ticketDTO) throws URISyntaxException {
        log.debug("REST request to save Ticket : {}", ticketDTO);
        if (ticketDTO.getId() != null) {
            throw new BadRequestException("A new ticket cannot already have an ID");
        }
        Ticket result = ticketService.save(ticketDTO);
        return ResponseEntity.created(new URI("/api/tickets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/tickets")
    public ResponseEntity<Ticket> updateTicket(@Valid @RequestBody Ticket ticketDTO) throws URISyntaxException {
        log.debug("REST request to update Ticket : {}", ticketDTO);
        if (ticketDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        Ticket result = ticketService.save(ticketDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ticketDTO.getId().toString()))
            .body(result);
    }


    @GetMapping("/tickets")
    public List<Ticket> getAllTickets() {
        log.debug("REST request to get all Tickets");
        return ticketService.findAll();
    }


    @GetMapping("/tickets/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
        log.debug("REST request to get Ticket : {}", id);
        Optional<Ticket> ticketDTO = ticketService.findOne(id);
        if (ticketDTO==null)
            throw new ResourceNotFoundException(ENTITY_NAME,"id",id);
        return ResponseEntity.ok(ticketDTO.get());
    }


    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        log.debug("REST request to delete Ticket : {}", id);
        ticketService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
