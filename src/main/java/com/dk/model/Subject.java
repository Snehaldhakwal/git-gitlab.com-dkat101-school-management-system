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
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "System input: The database generated ID")
    private @Getter Integer id;
    @ApiModelProperty(notes = "User input: The subject's name")
    private @Getter @Setter String name;
    @ApiModelProperty(notes = "User input: The subject's description")
    private @Getter @Setter String description;
    @CreationTimestamp
    @ApiModelProperty(notes = "System input: The database generated created on time")
    private @Getter LocalDateTime createdOn;
    @UpdateTimestamp
    @ApiModelProperty(notes = "System input: The database generated updated on time")
    private @Getter LocalDateTime updatedOn;

}
