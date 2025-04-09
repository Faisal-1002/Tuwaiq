package com.example.lab_11.Service;

import com.example.lab_11.Model.Category;
import com.example.lab_11.Model.Post;
import com.example.lab_11.Model.User;
import com.example.lab_11.Repositrory.CategoryRepository;
import com.example.lab_11.Repositrory.PostRepository;
import com.example.lab_11.Repositrory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Boolean addPost(Post post){
        Category category = categoryRepository.findCategoryById(post.getCategory_id());
        User user = userRepository.findUserById(post.getUser_id());
        if (category == null || user == null)
            return false;
        post.setPublish_date(LocalDate.now());
        postRepository.save(post);
        return true;
    }

    public Boolean updatePost(Integer id, Post post){
        Post oldPost = postRepository.findPostById(id);
        if (oldPost == null)
            return false;
        oldPost.setCategory_id(post.getCategory_id());
        oldPost.setTitle(post.getTitle());
        oldPost.setContent(post.getContent());
        oldPost.setUser_id(post.getUser_id());
        postRepository.save(oldPost);
        return true;
    }

    public Boolean deletePost(Integer id){
        Post post = postRepository.findPostById(id);
        if (post == null)
            return false;
        postRepository.delete(post);
        return true;
    }

    public List<Post> getAllPostsByUserId(Integer id){
        return postRepository.getAllPostsByUser_id(id);
    }

    public List<Post> getAllPostsByTitle(String title){
        return postRepository.findPostByTitle(title);
    }

    public List<Post> getAllPostsBeforeADate(LocalDate date){
        return postRepository.getAllPostsBeforeADate(date);
    }

    public List<Post> getAllPostsByCategoryId(Integer id){
        return postRepository.getAllPostsByCategoryId(id);
    }

}
