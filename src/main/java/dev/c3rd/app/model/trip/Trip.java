package dev.c3rd.app.model.trip;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "trips")
public class Trip {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String origin;

}
