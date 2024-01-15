package com.example.bssBack.controller;

import com.example.bssBack.service.RecmnderService;
import com.example.bssBack.utility.Security;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class RecmnderController {

    private RecmnderService recmnderService;

    public RecmnderController(RecmnderService recmnderService){
        this.recmnderService = recmnderService;
    }

    @GetMapping("/get/recmnd/num")
    public Integer GetHoleRecmndNum(@Param("cid") Long cid){
        return recmnderService.HoleRecmndNum(cid);
    }

    @GetMapping("/get/recmnd/num/view")
    public Integer GetRecmndNumAtView(@Param("cid") Long cid){
        return recmnderService.RecmndCnt(cid);
    }

    @PostMapping("/auth/recmnd/click")
    public ResponseEntity ControllRecmnd(@Param("cid") Long cid){

        String user_id = Security.getCurrentSid();

        if(!user_id.equals("anonymousUser") && !user_id.isEmpty()) {

            System.out.println(user_id + " " + cid);

            recmnderService.RecmndClick(user_id, cid);

            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "성공하였습니다.");
            return new ResponseEntity(result, HttpStatus.CREATED);

        }else{
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "로그인이 필요합니다");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }
}
