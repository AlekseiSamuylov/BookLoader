package com.samuylov.projectstart.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "COMMENT")
public class CommentEntity extends AbstractEntity {

    @NotNull
    private String text;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull
    private String name;

    @NotNull
    private Long bookId;
}
