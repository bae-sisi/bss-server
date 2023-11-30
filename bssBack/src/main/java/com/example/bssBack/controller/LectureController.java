package com.example.bssBack.controller;

import com.example.bssBack.entity.Lecture;
import com.example.bssBack.service.LectureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class LectureController {

    private LectureService lectureService;

    public LectureController(LectureService lectureService){
        this.lectureService = lectureService;
    }


    @PostMapping("/save/lecture")
    public ResponseEntity createLecture(@RequestParam("name") String name, @RequestParam("grade") Integer grade){
        try{
            Lecture lecture = new Lecture();

            lecture.setName(name);
            lecture.setGrade(grade);
            lectureService.Save(lecture);

            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "강의 등록에 성공하였습니다.");
            return new ResponseEntity(result, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "강의 등록 에 실패하였습니다.");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }

}
