package com.samuylov.projectstart.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public abstract class CommunityAbstractDto extends AbstractDto {
    private String text;
    private Date date;
    private String nickName;
    private Long bookId;
}
