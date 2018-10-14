package com.travelapp.service;


import com.travelapp.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {


    Ticket save(Ticket ticketDTO);

    /**
     * Get all the tickets.
     *
     * @return the list of entities
     */
    List<Ticket> findAll();


    /**
     * Get the "id" ticket.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Ticket> findOne(Long id);

    /**
     * Delete the "id" ticket.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
