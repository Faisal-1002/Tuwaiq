package com.example.newsarticle.Controller;

import com.example.newsarticle.Api.ApiResponse;
import com.example.newsarticle.Model.NewsArticle;
import com.example.newsarticle.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/newsarticle")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;

    @GetMapping("/get")
    public ResponseEntity getAllNewsArticles() {
        List<NewsArticle> newsArticles = newsArticleService.getNewsArticles();
        return ResponseEntity.status(HttpStatus.OK).body(newsArticles);
    }
    @PostMapping("/add")
    public ResponseEntity addNewsArticle(@RequestBody @Valid NewsArticle newsArticle, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        if (newsArticleService.addNewsArticle(newsArticle)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Successfully added news article"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to add news article"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateNewsArticle(@PathVariable String id, @RequestBody @Valid NewsArticle newsArticle, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        if (newsArticleService.updateNewsArticle(id, newsArticle)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Successfully updated news article"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to update news article"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNewsArticle(@PathVariable String id) {
        if (newsArticleService.deleteNewsArticle(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Successfully deleted news article"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to delete news article"));
    }
    @PutMapping("/publish/{id}")
    public ResponseEntity publishNewsArticle(@PathVariable String id) {
        if (newsArticleService.publishNewsArticle(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Successfully published news article"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to publish news article"));
    }
    @GetMapping("/getpublishednewsarticles")
    public ResponseEntity getPublishedNewsArticles() {
        List<NewsArticle> newsArticles = newsArticleService.getPublishedNewsArticles();
        return ResponseEntity.status(HttpStatus.OK).body(newsArticles);
    }
    @GetMapping("/getbycategory/{category}")
    public ResponseEntity getNewsArticleByCategory(@PathVariable String category) {
        List<NewsArticle> newsArticles = newsArticleService.getNewsArticlesByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(newsArticles);
    }

}
