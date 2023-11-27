package com.example.bssBack.service;

import com.example.bssBack.entity.Comment;
import com.example.bssBack.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.beans.Statement;
import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public Long Save(Comment comment){
       return commentRepository.save(comment).getCid();
    }

    public List<Comment> FindAll(Long progress_id){
       return commentRepository.FindCommentsByProgressID(progress_id);
    }

    public void IncresRecmnd(Long cid){
        commentRepository.IncreaseRecmndCnt(cid);
    }

    public Comment GetINFO(Long cid){
        return commentRepository.FindByID(cid);
    }

}
