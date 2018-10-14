package com.travelapp.service.impl;

import com.travelapp.model.Ticket;
import com.travelapp.repository.TicketRepository;
import com.travelapp.service.TicketService;
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
 * Service Implementation for managing Ticket.
 */
@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository ) {
        this.ticketRepository = ticketRepository;
    }


    @Override
    public Ticket save(Ticket ticket) {
        log.debug("Request to save Ticket : {}", ticket);

        ticket = ticketRepository.save(ticket);
        return ticket;
    }

    /**
     * Get all the tickets.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Ticket> findAll() {
        log.debug("Request to get all Tickets");
        return ticketRepository.findAll().stream()
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one ticket by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Ticket> findOne(Long id) {
        log.debug("Request to get Ticket : {}", id);
        return ticketRepository.findById(id)
           ;
    }

    /**
     * Delete the ticket by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ticket : {}", id);
        ticketRepository.deleteById(id);
    }
}
