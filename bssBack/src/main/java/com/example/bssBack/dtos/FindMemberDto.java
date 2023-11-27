package com.example.bssBack.dtos;

import java.time.LocalDateTime;

public interface FindMemberDto {

    Long getFid();
    String getTitle();

    String getContent();
    String getEnd_date();


    String getProf_name();

    String getLacture_name();
    LocalDateTime getCreated_at();
    Long getSid();
    String getUsername();
}
