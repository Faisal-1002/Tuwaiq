package com.example.lab_11.Service;

import com.example.lab_11.Model.Comment;
import com.example.lab_11.Model.Post;
import com.example.lab_11.Model.User;
import com.example.lab_11.Repositrory.CommentRepository;
import com.example.lab_11.Repositrory.PostRepository;
import com.example.lab_11.Repositrory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public Boolean addComment(Comment comment){
        User user = userRepository.findUserById(comment.getUser_id());
        Post post = postRepository.findPostById(comment.getPost_id());
        if (user == null || post == null)
            return false;
        comment.setComment_date(LocalDate.now());
        commentRepository.save(comment);
        return true;
    }

    public Boolean updateComment(Integer id, Comment comment){
        Comment oldComment = commentRepository.findCommentById(id);
        if (oldComment == null)
            return false;
        oldComment.setUser_id(comment.getUser_id());
        oldComment.setPost_id(comment.getPost_id());
        oldComment.setContent(comment.getContent());
        commentRepository.save(oldComment);
        return true;
    }

    public Boolean deleteComment(Integer id){
        Comment comment = commentRepository.findCommentById(id);
        if (comment == null)
            return false;
        commentRepository.delete(comment);
        return true;
    }

    public List<Comment> getCommentsByUserId(Integer id){
        return commentRepository.getCommentByUserId(id);
    }

    public List<Comment> getCommentsByPostId(Integer id){
        return commentRepository.getCommentByPostId(id);
    }

}
