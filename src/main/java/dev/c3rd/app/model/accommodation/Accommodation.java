package dev.c3rd.app.model.accommodation;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table
public class Accommodation {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private AccommodationType accommodationType;
    private String accommodationName;
    private Float price;
    private LocalDate startDate;
    private LocalDate endDate;


}
