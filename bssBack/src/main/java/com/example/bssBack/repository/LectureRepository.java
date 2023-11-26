package com.example.bssBack.repository;

import com.example.bssBack.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    Lecture findLectureByName(String name);
}
