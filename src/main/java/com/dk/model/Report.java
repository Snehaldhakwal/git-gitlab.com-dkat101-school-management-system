package com.dk.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter Long id;
    private @Getter @Setter Long studentId;
    private @Getter @Setter String term;
    private @Getter @Setter String scores;
    @CreationTimestamp
    private @Getter LocalDateTime createdOn;
    @UpdateTimestamp
    private @Getter LocalDateTime updatedOn;

}
