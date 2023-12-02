package com.example.bssBack.repository;

import com.example.bssBack.entity.Progress;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProgressRepository extends JpaRepository<Progress, Long> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
    value = "INSERT into progress(Pid, Lid, year) values((select p.Pid from professor as p where p.name = :prof)," +
            "(select l.lid from lecture as l where l.name = :lecture) , :year );")
    void SaveProgress(@Param("prof") String prof, @Param("lecture") String lecture, @Param("year") Integer year);

}
