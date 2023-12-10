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
    private Long Pid;

    private String name;

    @Column(name = "Is_officier")
    private Boolean IsOfficier;

    @Column(name = "Lab_into")
    private String LabINTRO;

    @Column(name = "edu_background")
    private String EduBackGround;


 /*   @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    private List<Progress> progresses = new ArrayList<>();*/

    private String office;
    private String phone;
    private String major;

    private String email;

    @Column(name = "Lab_Link")
    private String LabLink;

    protected Professor(){}

    public Professor(String name, Boolean IsOfficier, String LabINTRO,
                     String EduBackGround, String office, String phone,
                     String major, String LabLink, String email){

        this.name = name;
        this.IsOfficier = IsOfficier;
        this.LabINTRO = LabINTRO;
        this.EduBackGround = EduBackGround;
        this.office= office;
        this.phone = phone;
        this.major =major;
        this.LabLink = LabLink;
        this.email = email;
    }



}
