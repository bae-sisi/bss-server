package com.example.bssBack.controller;

import com.example.bssBack.entity.Written;
import com.example.bssBack.service.UserPageService;
import com.example.bssBack.utility.Security;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserPageController {

    private UserPageService userPageService;

    public UserPageController(UserPageService userPageService){
        this.userPageService = userPageService;
    }

    @GetMapping("/auth/get/written")
    public ResponseEntity GetUserWritten(@Param("page") Integer page){

        String user_id = Security.getCurrentSid();

        if(!user_id.equals("anonymousUser") && !user_id.isEmpty()){

            Integer start = (page-1)*3;
            Integer end = start + 3;

            List<Written> writtens =  userPageService.UserWrite(user_id, start, end, page);

            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "Event 삭제에 성공하였습니다.");
            result.put("data", writtens);
            return new ResponseEntity(result, HttpStatus.CREATED);

        }else{

            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "로그인이 필요한 작업입니다.");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }
}
