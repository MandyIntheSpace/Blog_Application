package com.application.blog.entities;

//import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private int categoryId;

    @Column(name = "title", length = 225, nullable = false)
   private String categoryTitle;

    @Column(name = "description")
   private String categoryDescription;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Post> postSet = new ArrayList<>();
}
