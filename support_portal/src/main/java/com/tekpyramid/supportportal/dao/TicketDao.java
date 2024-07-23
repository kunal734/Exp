package com.tekpyramid.supportportal.dao;

import com.tekpyramid.supportportal.data.models.entity.Ticket;

import java.util.List;

public interface TicketDao {

    List<Ticket> findAllTickets();

    int findCountOfTicketAssociatedWithUser(String userEmail);

}
