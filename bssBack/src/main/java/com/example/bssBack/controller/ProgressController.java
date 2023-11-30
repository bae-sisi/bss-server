package com.example.bssBack.controller;

import com.example.bssBack.dtos.EvaluationDto;
import com.example.bssBack.dtos.ProgressViewDto;
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

    private EvaluationService evaluationService;



    public ProgressController(ProgressService progressService, LectureService lectureService,
                              ProfessorService professorService, EvaluationService evaluationService){
        this.lectureService = lectureService;
        this.progressService = progressService;
        this.professorService = professorService;
        this.evaluationService = evaluationService;
    }

    @PostMapping("/make/progress")
    public ResponseEntity Make(@RequestParam("lecture") String lecture, @RequestParam("prof") String prof, @RequestParam("year") Integer year) throws Exception{

        try{
/*            Lecture lectures = lectureService.FindLeture(lecture);

            Professor professor = professorService.FindProfINFO(prof);

            Progress progress = new Progress();

            progress.setLid(lectures.getLid());
            progress.setPid(professor.getPid());

            System.out.println(progress);

            progressService.Save(progress);*/
            // 예전에 교과목명, 교수명으로 저장한게 있다면 못넣게 막는다 / 그냥 년도만 업댓되게 / 어,,, 그리고 년도 컬럼 추가 필요
            progressService.TestSave(lecture, prof, year);


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
    public List<ProgressViewDto> GetAllProgress(@RequestParam(value = "grade") Integer grade, @RequestParam( value = "index") String index){

        if(index.isEmpty() && index.isBlank()){
            index = null;
        }

        System.out.println(grade);
        System.out.println(index);
        return progressService.FindAll(grade, index);
    }


    @GetMapping("/get/avg/evaluation")
    public EvaluationDto GetEvaluationAVG(@RequestParam("id") Long id){
        return evaluationService.GetEvaluation(id);
    }


}

