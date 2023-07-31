package com.application.blog.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;
    @Column(name = "post_title", length = 225, nullable = false)
    private String title;
    @Column(name = "post_content", length = 225, nullable = false)
    private String content;
    @Column(name = "post_image", length = 225, nullable = false)
    private String imageName;
    @Column(name = "post_date", length = 225, nullable = false )
    private Date addedDate;
}
