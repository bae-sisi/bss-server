package com.example.bssBack.repository;

import com.example.bssBack.entity.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(nativeQuery = true, value = "Select Avg(c.rate) from comment as c where c.Sid = :id;")
    Double GetAvgRateBySID(@Param("id") Long id);

    @Query(nativeQuery = true, value = "Select * from comment as c where c.progress_id = :pgid order by c.cid DESC ;")
    List<Comment> FindCommentsByProgressID(@Param("pgid") Long pgid);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE comment as c set c.recmnd_Cnt = c.recmnd_Cnt+1 where c.cid = :id ; ")
    void IncreaseRecmndCnt(@Param("id") Long id);

    @Query(nativeQuery = true, value = "SELECT * from comment as c where c.cid = :id")
    Comment FindByID(@Param("id") Long id);
}
