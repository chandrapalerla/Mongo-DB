package com.ticketbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories("ua.epam.mishchenko.ticketbooking.repository.*")
//@EntityScan("ua.epam.mishchenko.ticketbooking.model.*")
public class TicketBookingApp {

    public static void main(String[] args) {
        SpringApplication.run(TicketBookingApp.class, args);
    }
}
