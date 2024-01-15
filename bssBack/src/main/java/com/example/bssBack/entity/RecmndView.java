package com.example.bssBack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "recmnd_view")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecmndView {
    @Id
    private Long cid;

    private Integer cnt;
}
