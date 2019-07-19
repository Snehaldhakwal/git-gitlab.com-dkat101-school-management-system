package com.dk.model;

import io.swagger.annotations.ApiModelProperty;
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
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "System input: The database generated ID")
    private @Getter Long id;
    @ApiModelProperty(notes = "User input: The attendance's user ID")
    private @Getter @Setter Long userId;
    @ApiModelProperty(notes = "User input: The attendance's type {arrival, departure}")
    private @Getter @Setter String type;
    @CreationTimestamp
    @ApiModelProperty(notes = "System input: The database generated created on time")
    private @Getter LocalDateTime createdOn;
    @UpdateTimestamp
    @ApiModelProperty(notes = "System input: The database generated updated on time")
    private @Getter LocalDateTime updatedOn;

}
