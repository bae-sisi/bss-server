package com.example.bssBack.service;

import com.example.bssBack.entity.Lecture;
import com.example.bssBack.repository.LectureRepository;
import org.springframework.stereotype.Service;

@Service
public class LectureService {

    private LectureRepository lectureRepository;


    public LectureService(LectureRepository lectureRepository){
        this.lectureRepository = lectureRepository;
    }


    public void Save(Lecture lecture) throws Exception{
        if(!lecture.getName().isBlank()){
            System.out.println(lecture);
            lectureRepository.save(lecture);
        }else{
            throw new Exception("Lecture name is Empty");
        }
    }

    public Lecture FindLeture(String name){
        return lectureRepository.findLectureByName(name);
    }
}
