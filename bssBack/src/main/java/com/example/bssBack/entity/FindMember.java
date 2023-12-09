package com.example.bssBack.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "findmember")

public class FindMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fid;

    private String title;
    private String content;

    @Column(name = "end_date")
    private String end_date;

    @Column(name = "prof_name")
    private String prof_name;

    @Column(name = "lecture_name")
    private String lecture_name;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "stack")
    private Integer stack;

    protected FindMember(){};

    public FindMember(String title, String content, String end_date, String prof_name, String lecture_name, String user_id, LocalDateTime created_at, Integer stack){
        this.title = title;
        this.content = content;
        this.end_date = end_date;
        this.prof_name = prof_name;
        this.lecture_name = lecture_name;
        this.user_id = user_id;
        this.created_at = created_at;
        this.stack = stack;
    }
}
