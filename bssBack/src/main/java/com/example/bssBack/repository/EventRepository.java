package com.example.bssBack.repository;

import com.example.bssBack.dtos.EventDto;
import com.example.bssBack.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(nativeQuery = true, value = "select * from event where event.eid = :id")
    Event findByEid(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select * from event e where e.title like %:index% or e.content like %:index% ;")
    List<Event> findEventsByContentContainsAndTitleContains(@Param("index") String index);

    @Query(nativeQuery = true, value = "select * from event as e where e.user_id = :sid;")
    List<Event> findEventsByUser_id(@Param("sid") Long sid);

    //하나의 Evnet Detail 정보 가져오기
    @Query(nativeQuery = true, value = "select e.eid, e.title, e.content, e.created_at, u.username, u.sid from event as e join user as u ON e.user_id = u.sid where e.eid = :eid ;")
    EventDto FindEventAndUserByEId(@Param("eid")Long eid);

    //전체 검색
    @Query(nativeQuery = true, value = "select e.eid, e.title, e.content, e.created_at, u.username, u.sid from event as e join user as u ON e.user_id = u.sid order by e.created_at DESC ;")
    List<EventDto> FindAll();

    //검색어를 통한 검색
    @Query(nativeQuery = true, value = "select e.eid, e.title, e.content, e.created_at, u.username, u.sid from event as e join user as u ON e.user_id = u.sid where e.title like %:index% or e.content like %:index% order by e.created_at DESC; ")
    List<EventDto> findEventUserConsistIndex(@Param("index") String index);

}
