package com.dk.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter Long id;
    private @Getter @Setter Long userId;
    private @Getter @Setter String type;
    @CreationTimestamp
    private @Getter LocalDateTime createdOn;
    @UpdateTimestamp
    private @Getter LocalDateTime updatedOn;

}
