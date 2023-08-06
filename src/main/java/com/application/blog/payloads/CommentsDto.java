package com.application.blog.payloads;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class CommentsDto {
    private int comId;
    private String content;
}
