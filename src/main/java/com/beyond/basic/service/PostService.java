package com.beyond.basic.service;

import com.beyond.basic.domain.post.Post;
import com.beyond.basic.domain.post.PostResDto;
import com.beyond.basic.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;

    @Autowired //생성자패턴, 생성자가 1개만 있을 때는 생략 가능
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

//    public List<Post> postList(){
//        try {
//            List<Post> list = postRepository.findAll();
//            return list;
//        }catch (NullPointerException e){
//            System.out.println(e.getMessage());;
//            return null;
//        }
//    }

    public List<PostResDto> postList() {
        try {
            List<Post> postList = postRepository.findAll();
            List<PostResDto> postResDtoList = new ArrayList<>();
            for (Post p : postList){
                postResDtoList.add(p.fromEntity());
            }
            return postResDtoList;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
