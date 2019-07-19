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
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "System input: The database generated ID")
    private @Getter Long id;
    @ApiModelProperty(notes = "User input: The mark's student ID")
    private @Getter @Setter Long studentId;
    @ApiModelProperty(notes = "User input: The mark's subject ID")
    private @Getter @Setter Long subjectId;
    @ApiModelProperty(notes = "User input: The mark's teacher ID")
    private @Getter @Setter Long teacherId;
    @ApiModelProperty(notes = "User input: The mark's score")
    private @Getter @Setter Integer score;
    @CreationTimestamp
    @ApiModelProperty(notes = "System input: The database generated created on time")
    private @Getter LocalDateTime createdOn;
    @UpdateTimestamp
    @ApiModelProperty(notes = "System input: The database generated updated on time")
    private @Getter LocalDateTime updatedOn;

}
