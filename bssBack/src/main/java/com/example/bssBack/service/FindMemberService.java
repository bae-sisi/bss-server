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
        return findMemberRepository.FindMembersAndUsersConsist(index);
    }

    public Long Save(FindMember findMember){
        return findMemberRepository.save(findMember).getFid();
    }

    public FindMember GetOneINFO(Long fid){
        return findMemberRepository.findFindMemberByFid(fid);
    }

    public void Deleted(FindMember findMember){
        findMemberRepository.delete(findMember);
    }


    public FindMemberDto GetONEDetailINFO(Long fid){
        return findMemberRepository.GetDetailINFO(fid);
    }

    public List<FindMemberDto> GetListFindMember(){
        return findMemberRepository.GetLimitFindMemberDTO();
    }
}
