package com.example.bssBack.controller;


import com.example.bssBack.entity.Comment;
import com.example.bssBack.entity.Evaluation;
import com.example.bssBack.service.CommentService;
import com.example.bssBack.service.EvaluationService;
import com.example.bssBack.utility.Security;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private CommentService commentService;

    private EvaluationService evaluationService;

    public CommentController(CommentService commentService, EvaluationService evaluationService){
        this.commentService = commentService;
        this.evaluationService = evaluationService;
    }

    @PostMapping("/auth/save/cmt")
    public ResponseEntity Save(@RequestParam("content") String content,
                              @RequestParam("enrollSems") String enrollSems,
                              @RequestParam("progress_id") Long progress_id,
                               @RequestParam("rate") Integer rate,
                               @RequestParam("assignment_freq") Integer assignment_freq,
                               @RequestParam("group_freq") Integer group_freq,
                               @RequestParam("grading") Integer grading,
                               @RequestParam("attending") Integer attending,
                               @RequestParam("exam_num") Integer exam_num){

        String user_id = Security.getCurrentSid();

        if(!user_id.equals("anonymousUser") && !user_id.isEmpty()){
            Comment comment = new Comment(content,enrollSems, 0, user_id, progress_id, rate);

            System.out.println(comment);

            Long cid = commentService.Save(comment);

            Evaluation evaluation = new Evaluation(cid, assignment_freq,group_freq,grading,attending,exam_num,progress_id);

            evaluationService.Save(evaluation);

            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "평가 작성에 성공하였습니다.");
            return new ResponseEntity(result, HttpStatus.CREATED);

        }else{
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "로그인이 필요합니다");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all/cmt")
    public List<Comment> GetAll( @RequestParam("progress_id") Long progress_id ){
        return commentService.FindAll(progress_id);
    }

    @GetMapping("/incres/recmnd/{cid}")
    public ResponseEntity IncresRecmnd(@PathVariable("cid") Long cid){
        try{
            Comment comment = commentService.GetINFO(cid);

            comment.setRecmndCnt(comment.getRecmndCnt()+1);

            commentService.Save(comment);

            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "추천 성공");
            return new ResponseEntity(result, HttpStatus.CREATED);

        }catch (Exception e){
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "추천 실패");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/auth/increase/recmnd/{cid}")
    public ResponseEntity IncrsRecmnd(@PathVariable("cid") Long cid){

        String user_id = Security.getCurrentSid();

        if(!user_id.equals("anonymousUser") && !user_id.isEmpty()) {
            commentService.IncresRecmnd(cid);

            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "추천에 성공하였습니다.");
            return new ResponseEntity(result, HttpStatus.CREATED);

        }else{
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "로그인이 필요합니다");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }


}
