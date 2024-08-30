package com.livingsync.annoucements.model;

import java.time.LocalDateTime;
import java.util.Map;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Poll {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @ElementCollection
    private Map<String, Integer> options;

    private LocalDateTime timestamp;
    
    private Long createdBy;
    // Getters, setters, and vote method
}
