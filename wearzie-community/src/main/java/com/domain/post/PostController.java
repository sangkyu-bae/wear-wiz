package com.domain.post;

import com.domain.post.request.RegisterPostRequest;
import com.domain.post.service.PostRegisterService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostRegisterService postRegisterService;

    @PostMapping("/community/v2")
    public ResponseEntity<Post> registerPost(@Valid @RequestBody RegisterPostRequest request,
                                             @RequestHeader("userId") Long userId,
                                             Errors errors){

        request.setMemberId(userId);

        if(errors.hasErrors()){
            //error 처리

        }


        Post createPost = postRegisterService.registerPost(request);

        return ResponseEntity.ok().body(createPost);
    }

    @GetMapping("/comment/v1/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable("postId")long postId){
        return null;
    }

}
