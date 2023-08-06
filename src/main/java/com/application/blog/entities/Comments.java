package com.application.blog.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int comId;
    private String content;
    @ManyToOne
    private Post post;

}
