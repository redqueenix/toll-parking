package com.assignment.repository;

import com.assignment.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Default Repository for the DAO Ticket
 */
@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {}
