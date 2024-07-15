package com.beyond.basic.controller;

import com.beyond.basic.domain.post.Post;
import com.beyond.basic.domain.post.PostResDto;
import com.beyond.basic.service.MemberService;
import com.beyond.basic.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
//@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

//    @GetMapping("/post/list")
//    public String PostList(Model model){
//        List<Post> postList = postService.postList();
//        model.addAttribute("postList",postList);
//        return "/post/post-list";
//    }

    @GetMapping("/post/list")
    @ResponseBody
    public List<PostResDto> postList(){
        return postService.postList();
    }

}
