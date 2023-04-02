package com.example.dreamcatch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173") // does not work
@Entity
@Table(name = "entries")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @ElementCollection
    @Column(columnDefinition = "VARCHAR(255)")
    private List<String> tags;

    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date date;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "energy", nullable = false)
    private int energy;

    @Column(name = "stress", nullable = false)
    private int stress;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
