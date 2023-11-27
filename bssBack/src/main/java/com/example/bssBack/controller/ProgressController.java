package com.example.bssBack.controller;

import com.example.bssBack.dtos.ProgressDto;
import com.example.bssBack.entity.ProgressView;
import com.example.bssBack.entity.Lecture;
import com.example.bssBack.entity.Professor;
import com.example.bssBack.entity.Progress;
import com.example.bssBack.service.EvaluationService;
import com.example.bssBack.service.LectureService;
import com.example.bssBack.service.ProfessorService;
import com.example.bssBack.service.ProgressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProgressController {

    private ProgressService progressService;

    private LectureService lectureService;

    private ProfessorService professorService;



    public ProgressController(ProgressService progressService, LectureService lectureService,
                              ProfessorService professorService, EvaluationService evaluationService){
        this.lectureService = lectureService;
        this.progressService = progressService;
        this.professorService = professorService;
    }

    @PostMapping("/make/progress")
    public ResponseEntity Make(@RequestParam("lecture") String lecture, @RequestParam("prof") String prof) throws Exception{

        System.out.println(lecture);
        System.out.printf(prof);

        try{
            Lecture lectures = lectureService.FindLeture(lecture);

            Professor professor = professorService.FindProfINFO(prof);

            Progress progress = new Progress();

            progress.setLid(lectures.getLid());
            progress.setPid(professor.getPid());

            System.out.println(progress);

            progressService.Save(progress);

            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "개설 교과목 등록에 성공하였습니다.");
            return new ResponseEntity(result, HttpStatus.ACCEPTED);

        }catch (Exception e){
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "개설 교과목 등록에 실패하였습니다.");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all/progress")
    public List<ProgressView> GetAllProgress(){
        return progressService.FindAll();
    }


}

