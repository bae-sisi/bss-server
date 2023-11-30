package com.example.bssBack.repository;

import com.example.bssBack.dtos.EvaluationDto;
import com.example.bssBack.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    @Query(nativeQuery = true,
    value = "SELECT CASE WHEN ev.AsignFreq > 2 THEN '많음' WHEN ev.AsignFreq > 1 THEN '보통' ELSE '없음' END AS `AF`,\n" +
            "  case when ev.GrpFreq > 2 then '많음' when ev.GrpFreq > 1 then '보통' ELSE '없음' END AS `GF`,\n" +
            "  case when ev.Grading > 2 then '너그러움' when ev.Grading > 1 then '보통' ELSE '깐깐함' END AS `GR`,\n" +
            "  case when ev.Attending > 4 then '복합적' when ev.Attending > 3 then '직접호명' when ev.Attending > 2 then '지정좌석' when ev.Attending > 1 then '전자출결' ELSE '반영안함' END AS `ATT`,\n" +
            "  case when ev.ExamN > 4 then '네번이상' when ev.ExamN > 3 then '세번' when ev.ExamN > 2 then '두번' when ev.ExamN > 1 then '한번' ELSE '없음' END AS `EX`\n" +
            "FROM ( SELECT AVG(assignment_freq) AS AsignFreq, AVG(group_freq) AS GrpFreq, AVG(grading) AS Grading, AVG(attending) AS Attending, AVG(exam_num) AS ExamN\n" +
            "  FROM evaluation WHERE pgid = :id ) AS ev;")
    EvaluationDto GetEvaluationByPGID(@Param("id") Long id);
}
