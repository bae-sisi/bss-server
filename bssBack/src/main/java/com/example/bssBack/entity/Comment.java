package com.example.bssBack.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    private String content;
    private Integer rate;

    @Column(name = "enroll_Sems")
    private String enrollSems;
    @Column(name = "recmnd_Cnt")
    private Integer recmndCnt;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "progress_id")
    private Long progress_id;

    protected Comment() {};

    public Comment(String content, String enrollSems, Integer recmndCnt, String user_id, Long progress_id, Integer rate){
        this.content = content;
        this.enrollSems = enrollSems;
        this.recmndCnt = recmndCnt;
        this.user_id = user_id;
        this.progress_id = progress_id;
        this.rate = rate;
    }
}
