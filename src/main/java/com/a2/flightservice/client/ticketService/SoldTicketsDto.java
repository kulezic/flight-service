package com.a2.flightservice.client.ticketService;

public class SoldTicketsDto {
    private Integer soldTickets;

    public SoldTicketsDto() {
    }

    public SoldTicketsDto(Integer soldTickets) {
        this.soldTickets = soldTickets;
    }

    public Integer getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(Integer soldTickets) {
        this.soldTickets = soldTickets;
    }
}
