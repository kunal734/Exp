package com.tekpyramid.supportportal.service.impl;

import com.tekpyramid.supportportal.dao.TicketDao;
import com.tekpyramid.supportportal.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketDao ticketDao;
}
