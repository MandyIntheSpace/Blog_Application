package com.application.blog.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
