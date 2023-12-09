package com.example.bssBack.dtos;

import java.time.LocalDateTime;

public interface FindMemberDto {

    Long getFid();
    String getTitle();

    String getContent();
    String getEnd_date();


    String getProf_name();

    String getLecture_name();
    LocalDateTime getCreated_at();

    Integer getStack();
    Long getSid();
    String getAuthor();

    String getAuthor_email();
}
