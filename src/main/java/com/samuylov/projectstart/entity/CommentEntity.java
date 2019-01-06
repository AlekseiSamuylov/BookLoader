package com.samuylov.projectstart.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "COMMENTS")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String text;

    @NonNull
    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull
    private String nickName;

    @NotNull
    private long bookId;
}
