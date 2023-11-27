package com.example.bssBack.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Data
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vid;

    private Long cid;

    @Column(name = "assignment_freq")
    private Integer assignment_freq;

    @Column(name = "group_freq")
    private Integer group_freq;

    private Integer grading;

    private Integer attending;

    private Integer exam_num;

    private Long pgid;


    public Evaluation(Long cid, Integer assignment_freq, Integer group_freq,
                      Integer grading, Integer attending, Integer exam_num, Long pgid){

        this.cid = cid;
        this.assignment_freq = assignment_freq;
        this.group_freq = group_freq;
        this.grading = grading;
        this.attending = attending;
        this.exam_num = exam_num;
        this.pgid = pgid;
    }

    public Evaluation() {

    }
}
