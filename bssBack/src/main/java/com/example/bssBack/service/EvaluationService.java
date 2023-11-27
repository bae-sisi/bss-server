package com.example.bssBack.service;

import com.example.bssBack.entity.Evaluation;
import com.example.bssBack.repository.EvaluationRepository;
import org.springframework.stereotype.Service;

@Service
public class EvaluationService {

    private EvaluationRepository evaluationRepository;

    public EvaluationService(EvaluationRepository evaluationRepository){
        this.evaluationRepository = evaluationRepository;
    }

    public void Save(Evaluation evaluation){

        /*evaluationRepository.Save(evaluation.getCid(), evaluation.getAssignment_freq(), evaluation.getGroup_freq(),
                evaluation.getGrading(), evaluation.getAttending(), evaluation.getExam_num(), evaluation.getPgid());*/
        evaluationRepository.save(evaluation);
    }
}
