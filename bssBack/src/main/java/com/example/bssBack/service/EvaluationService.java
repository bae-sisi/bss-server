package com.example.bssBack.service;

import com.example.bssBack.dtos.EvaluationDto;
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
        evaluationRepository.save(evaluation);
    }

    public EvaluationDto GetEvaluation(Long id){
        return evaluationRepository.GetEvaluationByPGID(id);
    }
}
