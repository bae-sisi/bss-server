package com.example.bssBack.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "recmnder")
public class Recmnder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "recmnd_id")
    private Long recmnd_id;

    @Column(name = "comment_id")
    private Long comment_id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "recmnd_at")
    private LocalDateTime recmnd_at;

    public Recmnder(Long comment_id, String user_id, LocalDateTime recmnd_at){
        this.comment_id = comment_id;
        this.user_id=user_id;
        this.recmnd_at = recmnd_at;
    }

    public Recmnder() {

    }
}
