package com.example.bssBack.repository;

import com.example.bssBack.entity.RecmndView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecmndViewRepository extends JpaRepository<RecmndView, Long> {

    @Query(nativeQuery = true, value = "select cnt from recmnd_view where cid = :cid ;")
    Integer CntByCid(@Param("cid")Long cid);

}
