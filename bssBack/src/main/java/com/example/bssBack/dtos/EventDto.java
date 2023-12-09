package com.example.bssBack.dtos;

import java.time.LocalDateTime;

public interface EventDto {
    Long getEid();
    String getTitle();
    String getContent();
    String getAuthor();
    String getSid();
    LocalDateTime getCreated_at();

}
