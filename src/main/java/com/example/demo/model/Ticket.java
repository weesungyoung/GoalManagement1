package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Ticket {
    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAvailCount() {
        return availCount;
    }

    public void setAvailCount(int availCount) {
        this.availCount = availCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Ticket() {}

    public Ticket(String title, String venue, int price, int availCount, Date startDate, Date endDate) {
        this.title = title;
        this.venue = venue;
        this.price = price;
        this.availCount = availCount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private Integer ticketId;
    private String title;
    private String venue;
    private int price;
    private int availCount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
}
