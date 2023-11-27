package com.example.bssBack.repository;

import com.example.bssBack.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

/*    @Query(nativeQuery = true, value = "insert INTO evaluation values(:cid, :assignment_freq, :group_freq," +
            ":grading, :attending, :exam_num, :pgid);")
    void Save(@Param("cid") Long cid, @Param("assignment_frep") Integer assignment_freq,
              @Param("group_freq") Integer group_freq, @Param("grading") Integer grading,
              @Param("attending") Integer attending, @Param("exam_num") Integer exam_num,
              @Param("pgid") Long pgid);*/
}
