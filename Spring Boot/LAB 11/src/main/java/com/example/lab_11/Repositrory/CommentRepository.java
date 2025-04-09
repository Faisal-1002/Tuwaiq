package com.example.lab_11.Repositrory;

import com.example.lab_11.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment findCommentById(Integer id);
    @Query("select c from Comment c where c.user_id=?1")
    List<Comment> getCommentByUserId(Integer id);
    @Query("select c from Comment c where c.post_id=?1")
    List<Comment> getCommentByPostId(Integer id);
    
}
