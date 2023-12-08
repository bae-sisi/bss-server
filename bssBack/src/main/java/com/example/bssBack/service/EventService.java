package com.example.bssBack.service;


import com.example.bssBack.dtos.EventDto;
import com.example.bssBack.entity.Event;
import com.example.bssBack.repository.EventRepository;
import jakarta.el.ELException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }


    public List<EventDto> GetALL(){
        return eventRepository.FindAll();
    }

    public List<EventDto> GetContainIndex(String index){
        return eventRepository.findEventUserConsistIndex(index);
    }


    public Long Save(Event event){
        return eventRepository.save(event).getEid();
    }

    public Event Find(Long eid){
        return eventRepository.findByEid(eid);
    }

    public void Deleted(Event event){
        eventRepository.delete(event);
    }


    public EventDto GetOne(Long eid){
        return eventRepository.FindEventAndUserByEId(eid);
    }


    public List<EventDto> GetLimitList(){
        return eventRepository.findEventDTOLimit();
    }
}
