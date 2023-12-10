package com.example.bssBack.repository;

import com.example.bssBack.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Professor findProfessorByName(String name);

    @Query(nativeQuery = true, value = "select p.name from professor as p where p.Pid = :id ;")
    String findProfessorNameByID(@Param("id")Long id);

    @Query(nativeQuery = true, value = "select * from professor as p where p.Is_official = 1;")
    List<Professor> findProfessorsByIsOfficier();
}
