package com.application.blog.entities;

//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comments> commentsSet = new HashSet<>();
}
