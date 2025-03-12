package com.example.newsarticle.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {

    @NotEmpty
    private String id;
    @NotEmpty
    @Size(max = 100)
    private String title;
    @NotEmpty
    @Size(min = 5, max = 20)
    private String author;
    @NotEmpty
    @Size(min = 201)
    private String content;
    @NotEmpty
    @Pattern(regexp = "^(politics|sports|technology)$")
    private String category;
    @NotEmpty
    private String imageUrl;
    @NotNull
    @AssertFalse
    private boolean isPublished;
    @NotNull
    private LocalDate publishDate;

}
