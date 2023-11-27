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

    @Column(name = "lacture_name")
    private String lacture_name;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "user_id")
    private String user_id;


    protected FindMember(){};

    public FindMember(String title, String content, String end_date, String prof_name, String lacture_name, String user_id, LocalDateTime created_at){
        this.title = title;
        this.content = content;
        this.end_date = end_date;
        this.prof_name = prof_name;
        this.lacture_name = lacture_name;
        this.user_id = user_id;
        this.created_at = created_at;
    }
}
