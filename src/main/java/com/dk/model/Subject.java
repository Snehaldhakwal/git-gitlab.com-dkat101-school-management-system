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
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter Integer id;
    private @Getter @Setter String name;
    private @Getter @Setter String description;
    @CreationTimestamp
    private @Getter LocalDateTime createdOn;
    @UpdateTimestamp
    private @Getter LocalDateTime updatedOn;

}
