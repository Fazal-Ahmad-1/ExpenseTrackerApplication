package com.FazalProject.ExpenseTracker.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eid;

    @Column(nullable = false)
    @NotBlank(message = "Product Name cannot be null!")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Product type cannot be null!")
    private String type;

    @Column(nullable = false)
    private double quantity;

    @Column(nullable = false)
    private double price;

    private String note;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "Payment_Mode")
    private PaymentMode mode;

//    @Column(nullable = false,name = "Date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
