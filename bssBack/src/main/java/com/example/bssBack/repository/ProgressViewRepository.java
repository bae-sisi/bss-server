package com.example.bssBack.repository;

import com.example.bssBack.dtos.ProgressViewDto;
import com.example.bssBack.entity.ProgressView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgressViewRepository extends JpaRepository<ProgressView, Long> {

    @Query(nativeQuery = true, value = "select pv.progressID, AVG(c.rate) as Rate, pv.grade, pv.lecture_Name, pv.prof_Name, pv.year from comment as c join progress_view as pv on c.progress_id = pv.progressID group by c.progress_id;")
    List<ProgressViewDto> FindAllProgress();

    @Query(nativeQuery = true, value = "select pv.progressID, AVG(c.rate) as Rate, pv.grade, pv.lecture_Name, pv.prof_Name, pv.year from comment as c join progress_view as pv on c.progress_id = pv.progressID where pv.lecture_Name like %:index% group by c.progress_id;")
    List<ProgressViewDto> FindConsistIndexProgress(@Param("index") String index);

    @Query(nativeQuery = true, value = "select pv.progressID, AVG(c.rate) as Rate, pv.grade, pv.lecture_Name, pv.prof_Name, pv.year from comment as c join progress_view as pv on c.progress_id = pv.progressID where pv.grade = :grade group by c.progress_id;")
    List<ProgressViewDto> FindProgressesByGrade(@Param("grade") Integer grade);

    @Query(nativeQuery = true, value = "select pv.progressID, AVG(c.rate) as Rate, pv.grade, pv.lecture_Name, pv.prof_Name, pv.year from comment as c join progress_view as pv on c.progress_id = pv.progressID where pv.lecture_Name like %:index% and pv.grade = :grade group by c.progress_id;")
    List<ProgressViewDto> FindProgressesByGradeANDConsistIndex(@Param("grade") Integer grade, @Param("index") String index);

    @Query(nativeQuery = true, value = "SELECT CASE WHEN EXISTS ( SELECT 1 FROM progress AS pr WHERE pr.Pid = (SELECT p.Pid FROM professor AS p WHERE p.name = :prof)" +

            "AND pr.Lid = (SELECT l.lid FROM lecture AS l WHERE l.name = :lecture) )" +
            "THEN 'False' ELSE 'True' END AS Result;")
    Boolean IsExistsProgress(@Param("prof") String prof, @Param("lecture") String lecture);

}
