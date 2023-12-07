package com.example.bssBack.repository;

import com.example.bssBack.dtos.FindMemberDto;
import com.example.bssBack.entity.FindMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FindMemberRepository extends JpaRepository<FindMember, Long> {


    //전체 조회
    @Query(nativeQuery = true, value = "select f.fid, f.title, f.content, f.end_date, f.prof_name," +
            "f.lacture_name, f.created_at, f.stack, u.sid, u.username from findmember as f join " +
            "user as u on f.user_id = u.sid order by f.created_at DESC ;")
    List<FindMemberDto> findFindMembersAndUsers();


    //index를 포함하고 있는 data 반환
    @Query(nativeQuery = true, value = "select f.fid, f.title, f.content, f.end_date, f.prof_name," +
            "f.lacture_name, f.created_at, f.stack, u.sid, u.username from findmember as f join " +
            "user as u on f.user_id = u.sid where f.title like %:index% or f.content like %:index% order by f.created_at DESC ;")
    List<FindMemberDto> FindMembersAndUsersConsist(@Param("index") String index);


    // 유저가 작성한 모든 글 가져오기
    @Query(nativeQuery = true, value = "select * from findmember as f where f.uesr_id = :sid")
    List<FindMember> findFindMembersByUser_id(@Param("sid") String sid);


    //하나의 findmember 조회
    @Query(nativeQuery = true, value = "select * from findmember as f where f.fid = :id")
    FindMember findFindMemberByFid(@Param("id") Long id);


    @Query(nativeQuery = true, value = "select f.fid, f.title, f.content, f.end_date, f.prof_name," +
            "f.lacture_name, f.created_at, f.stack, u.sid, u.username from findmember as f join " +
            "user as u on f.user_id = u.sid where f.fid = :fid ;")
    FindMemberDto GetDetailINFO(@Param("fid") Long fid);

}
