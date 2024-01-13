package com.example.bssBack.controller;

import com.example.bssBack.dtos.EventDto;
import com.example.bssBack.entity.Event;
import com.example.bssBack.service.EventService;
import com.example.bssBack.utility.Security;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @GetMapping("/get/all/event")
    public List<EventDto> GetEvents(@RequestParam(value = "index", required = false) String index, @RequestParam(value = "page") Integer page){

        // 1page:0~9, 2page:10~19,

        Integer start = (page-1) * 10;

        System.out.println(start);

        if( index == null || index.isEmpty() || index.isBlank() ){
            return eventService.GetALL(start);
        }else{
            return eventService.GetContainIndex(index, start);
        }
    }

    @PostMapping("/auth/save/event")
    public ResponseEntity Create( @RequestBody Event event) throws Exception {

        String user_id = Security.getCurrentSid();

        if(!user_id.equals("anonymousUser") && !user_id.isEmpty()){
            LocalDateTime dateTime = LocalDateTime.now();

            System.out.println(dateTime);

            event.setCreated_at(dateTime);
            event.setUser_id(user_id);

            Long eid = eventService.Save(event);

            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "Event 저장에 성공하였습니다.");
            result.put("eid", eid);
            return new ResponseEntity(result, HttpStatus.CREATED);
        }else{

            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "Event 저장 실패/로그인이 필요합니다");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/auth/delete/event")
    public ResponseEntity Delete(@RequestParam("eid") Long eid){

        String user_id = Security.getCurrentSid();

        Event event = eventService.Find(eid);

        if(!user_id.equals("anonymousUser") && !user_id.isEmpty()){
            // 000의 자리는 관리자 ID일 경우를 상정함
            if(user_id.equals(event.getUser_id()) || user_id.equals("000")){
                eventService.Deleted(event);

                HashMap<String, Object> result = new HashMap<>();
                result.put("result", "Event 삭제에 성공하였습니다.");
                return new ResponseEntity(result, HttpStatus.CREATED);
            }else {
                HashMap<String, Object> result = new HashMap<>();
                result.put("result", "Event 삭제 실패");
                return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
            }
        }else {
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "로그인이 필요한 작업입니다.");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/auth/update/event")
    public ResponseEntity Update(@RequestBody Event events) throws Exception {

        String user_id = Security.getCurrentSid();

        Event event = eventService.Find(events.getEid());

        if(!user_id.equals("anonymousUser") && !user_id.isEmpty()){
            if(user_id.equals(event.getUser_id()) || user_id.equals("000")){

                event.setTitle(events.getTitle());
                event.setContent(events.getContent());

                eventService.Save(event);

                HashMap<String, Object> result = new HashMap<>();
                result.put("result", "Event update에 성공하였습니다.");
                return new ResponseEntity(result, HttpStatus.CREATED);

            }else{
                HashMap<String, Object> result = new HashMap<>();
                result.put("result", "Evnet update 실패");
                return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
            }
        }else{
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "로그인이 필요합니다");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/one/event")
    public EventDto GetOneEvent(@RequestParam(value = "eid") Long eid){
        System.out.println(eid);
        return eventService.GetOne(eid);
    }

    @GetMapping("/get/limit/event")
    public List<EventDto> GetLimitEvent(){
        return eventService.GetLimitList();
    }

}
