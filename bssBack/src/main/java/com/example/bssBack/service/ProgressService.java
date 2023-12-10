package com.example.bssBack.service;

import com.example.bssBack.entity.Progress;
import com.example.bssBack.entity.ProgressView;
import com.example.bssBack.repository.ProgressRepository;
import com.example.bssBack.repository.ProgressViewRepository;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
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

    public List<ProgressView> FindAll(Integer grade, String Index){

        if(grade==0 && Index == null){
            //검색 조건 없을때 전채 가져오기
            System.out.println("get All");
            return  progressViewRepository.FindAllProgress();
        }else if(grade != 0 && (Index == null || Index.isBlank())){
            // 검색 조건 : 학년만 있을떄
            System.out.println("get by grade");
            return  progressViewRepository.FindProgressesByGrade(grade);
        }else if(grade ==0 && Index != null && !Index.isBlank()){
            // 검색 조건 : 검색어만 있을때
            System.out.println("get by index");
            return progressViewRepository.FindConsistIndexProgress(Index);
        }

        // 검색 조건 : 학년과 검색어가 전부 있을때
        System.out.println("get by grade & index");
        return progressViewRepository.FindProgressesByGradeANDConsistIndex(grade, Index);
    }


    public void TestSave(String lecture, String prof, Integer year){
        Long pid = progressViewRepository.IsExistsProgress(prof, lecture);
        if(pid != 0){
            Progress progress = progressRepository.findById(pid).get();
            if(progress.getYear() <= year){
                progress.setYear(year);
            }
            progressRepository.save(progress);
        }else{
            progressRepository.SaveProgress(prof, lecture, year);
        }
    }

    public List<ProgressView> FindCorrespondProgress(String index, Integer grade){
        Integer year = Year.now().getValue() -2000;

        List<ProgressView> progressViewList = new ArrayList<>();

        if((index == null || index.isBlank() || index.isEmpty())){
            if(grade == 0){
                progressViewList = progressViewRepository.FindProgressesByYear(year);
            }else{
                progressViewList = progressViewRepository.FindProgressesByYearAndGrade(year, grade);
            }
        }else{
            if(grade== 0){
                progressViewList = progressViewRepository.FindProgressesByYearAndString(year, index);
            }else{
                progressViewList = progressViewRepository.FindProgressesByYearAndGradeAndString(year, grade, index);
            }
        }

        return progressViewList;
    }


    public ProgressView GetDetail(Long pid){
        return progressViewRepository.GetONEINFO(pid);
    }
}
