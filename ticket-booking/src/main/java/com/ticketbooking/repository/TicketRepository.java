package com.ticketbooking.repository;

import com.ticketbooking.model.Category;
import com.ticketbooking.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {

    Page<Ticket> getAllByUserId(Pageable pageable, Long userId);

    Page<Ticket> getAllByEventId(Pageable pageable, Long eventId);

    Boolean existsByEventIdAndPlaceAndCategory(Long eventId, Integer place, Category category);
}
