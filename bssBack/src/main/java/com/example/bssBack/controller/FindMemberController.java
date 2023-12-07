package com.example.bssBack.controller;

import com.example.bssBack.dtos.EventDto;
import com.example.bssBack.dtos.FindMemberDto;
import com.example.bssBack.entity.FindMember;
import com.example.bssBack.service.FindMemberService;
import com.example.bssBack.utility.Security;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FindMemberController {

    private FindMemberService findMemberService;

    public FindMemberController(FindMemberService findMemberService){
        this.findMemberService = findMemberService;
    }

    @GetMapping("/get/all/fidmem")
    public List<FindMemberDto> GetAllFindMember(@RequestParam(value = "index", required = false) String index){
        if( index == null || index.isEmpty() || index.isBlank() ){
            return findMemberService.GetAll();
        }else{
            System.out.println(index);
            return findMemberService.GetConsistIndex(index);
        }
    }


    @PostMapping("/auth/save/fidmem")
    public ResponseEntity Save(@RequestParam("title") String title,
                               @RequestParam("content") String content,
                               @RequestParam("end_date") String end_date,
                               @RequestParam("prof_name") String prof_name,
                               @RequestParam("lacture_name") String lacture_name){

        String user_id = Security.getCurrentSid();

        if(!user_id.equals("anonymousUser") && !user_id.isEmpty()){

            LocalDateTime dateTime = LocalDateTime.now();

            FindMember findMember = new FindMember(title,content,end_date,prof_name,lacture_name,user_id, dateTime);

            findMemberService.Save(findMember);
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "FindMember 저장에 성공하였습니다.");
            return new ResponseEntity(result, HttpStatus.CREATED);
        }else{
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "FindMember 저장 실패/로그인이 필요합니다");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/auth/delete/fidmem")
    public ResponseEntity Delete(@RequestParam("fid") Long fid){

        String user_id = Security.getCurrentSid();

        FindMember findMember = findMemberService.GetOneINFO(fid);

        if(!user_id.equals("anonymousUser") && !user_id.isEmpty()){

            if(user_id.equals(findMember.getUser_id()) || user_id.equals("000")){
                findMemberService.Deleted(findMember);

                HashMap<String, Object> result = new HashMap<>();
                result.put("result", "fidmem 삭제에 성공하였습니다.");
                return new ResponseEntity(result, HttpStatus.CREATED);
            }else{
                HashMap<String, Object> result = new HashMap<>();
                result.put("result", "delete 실패!");
                return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
            }

        }else{
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "로그인이 필요한 작업입니다");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/auth/update/fidmem")
    public ResponseEntity Update(@RequestParam("fid") Long fid,
                                 @RequestParam("title") String title,
                                 @RequestParam("content") String content,
                                 @RequestParam("end_date") String end_date,
                                 @RequestParam("prof_name") String prof_name,
                                 @RequestParam("lacture_name") String lacture_name){

        String user_id = Security.getCurrentSid();
        FindMember findMember = findMemberService.GetOneINFO(fid);

        if(!user_id.equals("anonymousUser") && !user_id.isEmpty()){
            if(user_id.equals(findMember.getUser_id()) || user_id.equals("000")){

                findMember.setTitle(title);
                findMember.setContent(content);
                findMember.setEnd_date(end_date);
                findMember.setProf_name(prof_name);
                findMember.setLacture_name(lacture_name);

                findMemberService.Save(findMember);

                HashMap<String, Object> result = new HashMap<>();
                result.put("result", "업데이트가 성공하였습니다.");
                return new ResponseEntity(result, HttpStatus.CREATED);

            }else{
                HashMap<String, Object> result = new HashMap<>();
                result.put("result", "업데이트에 실패했습니다.");
                return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
            }
        }else{
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "로그인이 필요한 기능입니다");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }

    }

    //하나의 Detail FindMember 정보 얻기
    @GetMapping("/get/one/fidmem")
    public FindMemberDto GetOneFindMember(@RequestParam(value = "fid") Long fid){
        System.out.println(fid);
        return findMemberService.GetONEDetailINFO(fid);
    }


}
