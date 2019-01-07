package com.samuylov.projectstart.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CommentDto {
    private long id;
    private String text;
    private Date date;
    private String nickName;
    private long bookId;
}
