package com.example.lab_11.Repositrory;

import com.example.lab_11.Model.Post;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findPostById(Integer id);
    List<Post> findPostByTitle(String title);
    @Query("select p from Post p where p.user_id=?1")
    List<Post> getAllPostsByUser_id(Integer id);
    @Query("select p from Post p where p.publish_date<?1")
    List<Post> getAllPostsBeforeADate(LocalDate date);
    @Query("select p from Post p where p.category_id=?1")
    List<Post> getAllPostsByCategoryId(Integer id);

}
