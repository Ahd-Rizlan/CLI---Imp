package com.example.oopcw.ticketingsystem.model;

import com.example.oopcw.ticketingsystem.constant.TicketStatus;
import com.example.oopcw.ticketingsystem.validation.AutoIdGeneration;

public class Ticket {
    private static final AutoIdGeneration ticketAutoIdGeneration = new AutoIdGeneration(0);

    private String ticketId;
    private TicketStatus status;


    public Ticket(Vendor Vendor) {
        this.ticketId = ticketAutoIdGeneration.generateAutoId("TId");
        this.status = TicketStatus.PENDING;
    }

    public String getTicketId() {
        return ticketId;
    }


    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket [ticketId=" + ticketId + "]";
    }
}


