package com.example.bssBack.service;

import com.example.bssBack.entity.ProgressView;
import com.example.bssBack.entity.Progress;
import com.example.bssBack.repository.ProgressRepository;
import com.example.bssBack.repository.ProgressViewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressService {

    private ProgressRepository progressRepository;

    private ProgressViewRepository progressViewRepository;

    public ProgressService(ProgressRepository progressRepository,
                           ProgressViewRepository progressViewRepository){
        this.progressRepository = progressRepository;
        this.progressViewRepository = progressViewRepository;
    }

    public void Save(Progress progress){
       progressRepository.save(progress);
    }

    public List<ProgressView> FindAll(){
        return progressViewRepository.findAll();
    }

}
