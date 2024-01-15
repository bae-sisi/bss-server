package com.example.bssBack.repository;

import com.example.bssBack.entity.Recmnder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecmnderRepository extends JpaRepository<Recmnder, Long> {

    @Query(nativeQuery = true, value = "select Num(*) from recmnder as r where r.comment_id = :cid ;")
    Integer findRecmndNumByCid(@Param("cid")Long cid);

    @Query(nativeQuery = true, value = "SELECT IFNULL(recmnd_id, 0) AS result FROM recmnder WHERE user_id = :sid AND comment_id = :cid ;")
    Long checkRecmndBySidAndCid(@Param("sid")String sid, @Param("cid")Long cid);

    @Query(nativeQuery = true, value = "select * from recmnder where recmnd_id = :rid")
    Recmnder findRecmnderByRid(@Param("rid") Long rid);
}
