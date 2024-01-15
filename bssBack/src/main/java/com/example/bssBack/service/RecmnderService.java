package com.example.bssBack.service;

import com.example.bssBack.entity.Recmnder;
import com.example.bssBack.repository.RecmndViewRepository;
import com.example.bssBack.repository.RecmnderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RecmnderService {

    private RecmnderRepository recmnderRepository;

    private RecmndViewRepository recmndViewRepository;

    public RecmnderService(RecmnderRepository recmnderRepository, RecmndViewRepository recmndViewRepository){

        this.recmnderRepository = recmnderRepository;
        this.recmndViewRepository = recmndViewRepository;
    }

    public Integer HoleRecmndNum(Long cid){
        return recmnderRepository.findRecmndNumByCid(cid);
    }

    public Integer RecmndCnt(Long cid){
        return recmndViewRepository.CntByCid(cid);
    }

    public void RecmndClick(String sid, Long cid){
        Long checkbit = recmnderRepository.checkRecmndBySidAndCid(sid, cid);

        LocalDateTime dateTime = LocalDateTime.now();

        if(checkbit == null ){
            Recmnder recmnder = new Recmnder(cid, sid, dateTime);
            recmnderRepository.save(recmnder);
        }else{
            Recmnder del = recmnderRepository.findRecmnderByRid(checkbit);
            recmnderRepository.delete(del);
        }
    }

}
