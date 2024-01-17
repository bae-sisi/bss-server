package com.example.bssBack.service;

import com.example.bssBack.entity.Event;
import com.example.bssBack.entity.FindMember;
import com.example.bssBack.entity.Written;
import com.example.bssBack.repository.EventRepository;
import com.example.bssBack.repository.FindMemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserPageService {

    private EventRepository eventRepository;
    private FindMemberRepository findMemberRepository;

    public UserPageService(EventRepository eventRepository, FindMemberRepository findMemberRepository){
        this.eventRepository= eventRepository;
        this.findMemberRepository = findMemberRepository;
    }

    public List<Written> UserWrite(String sid, Integer start, Integer end, Integer page){

        List<Written> writtens = new ArrayList<>();

        Integer eventNum = eventRepository.CntEventBySid(sid);

        Integer padding = 3-eventNum % 3;
        Integer denominator = eventNum / 3;

        if(padding != 0 ){
            denominator += 1;
        }

        System.out.println(padding + " ---- " + denominator);

        List<Event> eventList = eventRepository.findEventListBySid(sid, 3, start);

        Integer FOfs = 3 - eventList.size();

        if(FOfs > 0 ){
            Integer Fstart = 0;

            if(FOfs == 3){
                Fstart = padding + (page-denominator-1)*3;
            }

            System.out.printf(FOfs + "_____"+Fstart+"\n");

            List<FindMember> findMemberList = findMemberRepository.findFindMemberListBySid(sid, FOfs, Fstart);

            System.out.printf(eventList.size() + " "+ findMemberList.size() + "\n");
        }

        for(Event e:eventList){

        }

        return writtens;
    }

}
