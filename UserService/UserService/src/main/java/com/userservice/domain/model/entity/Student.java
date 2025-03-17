package com.userservice.domain.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
public class Student {
    @Id
    private String id;
    private int rollNo;
    private String name;
    private String lastName;
    private String standard;

}
