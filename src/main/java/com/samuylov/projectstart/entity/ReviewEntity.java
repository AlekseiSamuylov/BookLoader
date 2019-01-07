package com.samuylov.projectstart.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "REVIEW")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String text;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull
    private String nickName;

    @NotNull
    private long bookId;
}