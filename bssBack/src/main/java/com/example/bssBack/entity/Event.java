package com.example.bssBack.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eid;

    private String title;
    private String content;
    private LocalDateTime created_at;
    private String user_id;


    protected Event(){}

    public Event(String title, String content, LocalDateTime dateTime, String user_id){
        this.title = title;
        this.content = content;
        this.created_at = dateTime;
        this.user_id = user_id;
    }
}
