package com.example.bssBack.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Pid")
    private Long pid;

    private String name;

    @Column(name = "Is_official")
    private Boolean isOfficial;

    @Column(name = "Lab_intro")
    private String labIntro;

    @Column(name = "edu_background")
    private String eduBackground;


 /*   @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    private List<Progress> progresses = new ArrayList<>();*/

    private String office;
    private String phone;
    private String major;

    private String email;

    @Column(name = "Lab_url")
    private String labUrl;

    protected Professor(){}

    public Professor(String name, Boolean isOfficial, String labIntro,
                     String eduBackground, String office, String phone,
                     String major, String labUrl, String email){

        this.name = name;
        this.isOfficial = isOfficial;
        this.labIntro = labIntro;
        this.eduBackground = eduBackground;
        this.office= office;
        this.phone = phone;
        this.major =major;
        this.labUrl = labUrl;
        this.email = email;
    }



}
