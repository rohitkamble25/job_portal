package com.example.jobpotral2.model;

import java.time.LocalDate;

import org.hibernate.internal.build.AllowNonPortable;
import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@Table(name = "jobs")
@Entity
@NoArgsConstructor
@Data
@AllowNonPortable
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;
    private String location;
    private String description;
    private String salary;

    private LocalDate postedDate = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private User recruiter;

}
