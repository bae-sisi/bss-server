package com.example.bssBack.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "progress")
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long Pid;

    private Long Lid;


/*    @ManyToOne
    @JoinColumn(name = "Pid")
    private Professor professor;*/

}
