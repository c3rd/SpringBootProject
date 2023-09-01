package dev.c3rd.app.model.itinerary;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table
public class Itinerary {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private TransportationType transportationType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String destination;

}
