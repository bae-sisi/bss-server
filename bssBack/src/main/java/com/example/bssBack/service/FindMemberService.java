package com.example.bssBack.service;


import com.example.bssBack.dtos.FindMemberDto;
import com.example.bssBack.entity.FindMember;
import com.example.bssBack.repository.FindMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindMemberService {

    private FindMemberRepository findMemberRepository;

    public FindMemberService(FindMemberRepository findMemberRepository){
        this.findMemberRepository = findMemberRepository;
    }

    public List<FindMemberDto> GetAll(){
        return findMemberRepository.findFindMembersAndUsers();
    }

    public List<FindMemberDto> GetConsistIndex(String index){
        return findMemberRepository.findFindMembersAndUsersConsist(index);
    }

    public void Save(FindMember findMember){
        findMemberRepository.save(findMember);
    }

    public FindMember GetOneINFO(Long fid){
        return findMemberRepository.findFindMemberByFid(fid);
    }

    public void Deleted(FindMember findMember){
        findMemberRepository.delete(findMember);
    }
}
