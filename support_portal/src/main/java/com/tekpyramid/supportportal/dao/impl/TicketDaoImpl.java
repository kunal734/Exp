package com.tekpyramid.supportportal.dao.impl;

import com.tekpyramid.supportportal.dao.TicketDao;
import com.tekpyramid.supportportal.data.models.entity.Ticket;
import com.tekpyramid.supportportal.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TicketDaoImpl implements TicketDao {

    private final TicketRepository ticketRepository;

    @Override
    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public int findCountOfTicketAssociatedWithUser(String userEmail) {
        List<Ticket> ticketList = ticketRepository.findByAssignedToEmail(userEmail);
        return ticketList.size();
    }
}
