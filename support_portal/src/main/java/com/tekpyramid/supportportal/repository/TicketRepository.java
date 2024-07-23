package com.tekpyramid.supportportal.repository;

import com.tekpyramid.supportportal.data.models.entity.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TicketRepository extends MongoRepository<Ticket, String> {

    List<Ticket> findByAssignedToEmail(String userEmail);

}
