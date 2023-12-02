package com.example.bssBack.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Table(name = "progress_view")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgressView {
    @Id
    private Long progressID;

    private Integer grade;

    private Integer year;

    @Column(name = "prof_Name")
    private String profName;

    @Column(name = "lecture_Name")
    private String lectureName;

    @Column(name = "rate")
    private Double rate;
}
