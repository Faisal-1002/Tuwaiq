package com.example.newsarticle.Service;

import com.example.newsarticle.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewsArticleService {

    ArrayList<NewsArticle> newsArticles = new ArrayList<>();

    public ArrayList<NewsArticle> getNewsArticles() {
        return newsArticles;
    }

    public boolean addNewsArticle(NewsArticle newsArticle) {
        for (NewsArticle newsArticle1 : newsArticles) {
            if (newsArticle1.getId().equals(newsArticle.getId())) {
                return false;
            }
        }
        newsArticles.add(newsArticle);
        return true;
    }

    public boolean updateNewsArticle(String id, NewsArticle newsArticle) {
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId().equals(id)) {
                newsArticles.set(i, newsArticle);
                return true;
            }
        }
        return false;
    }

    public boolean deleteNewsArticle(String id) {
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId().equals(id)) {
                newsArticles.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean publishNewsArticle(String id) {
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId().equals(id)) {
                newsArticles.get(i).setPublished(true);
                return true;
            }
        }
        return false;
    }

    public ArrayList<NewsArticle> getPublishedNewsArticles() {
        ArrayList<NewsArticle> publishedNewsArticles = new ArrayList<>();
        for (NewsArticle newsArticle : newsArticles) {
            if (newsArticle.isPublished()) {
                publishedNewsArticles.add(newsArticle);
            }
        }
        return publishedNewsArticles;
    }

    public ArrayList<NewsArticle> getNewsArticlesByCategory(String category) {
        ArrayList<NewsArticle> newsArticlesByCategory = new ArrayList<>();
        for (NewsArticle newsArticle : newsArticles) {
            if (newsArticle.getCategory().equals(category)) {
                newsArticlesByCategory.add(newsArticle);
            }
        }
        return newsArticlesByCategory;
    }

}
