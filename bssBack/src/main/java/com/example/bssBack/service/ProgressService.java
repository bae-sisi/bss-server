package com.example.bssBack.service;

import com.example.bssBack.dtos.ProgressViewDto;
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

    public List<ProgressViewDto> FindAll(Integer grade, String Index){

        if(grade==0 && Index == null){
            //검색 조건 없을때 전채 가져오기
            return  progressViewRepository.FindAllProgress();
        }else if(grade != 0 && (Index == null || Index.isBlank())){
            // 검색 조건 : 학년만 있을떄
            return  progressViewRepository.FindProgressesByGrade(grade);
        }else if(grade ==0 && Index != null && !Index.isBlank()){
            // 검색 조건 : 검색어만 있을때
            return progressViewRepository.FindConsistIndexProgress(Index);
        }

        // 검색 조건 : 학년과 검색어가 전부 있을때
        return progressViewRepository.FindProgressesByGradeANDConsistIndex(grade, Index);
    }




}
