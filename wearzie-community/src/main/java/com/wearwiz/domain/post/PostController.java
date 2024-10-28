package com.wearwiz.domain.post;

import com.wearwiz.domain.post.request.FindPagingPostRequest;
import com.wearwiz.domain.post.request.RegisterPostRequest;
import com.wearwiz.domain.post.service.FindPostService;
import com.wearwiz.domain.post.service.PostRegisterService;
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

    private final FindPostService findPostService;

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
        Post post = findPostService.findPostById(postId);

        return ResponseEntity.ok().body(post);
    }

    @GetMapping("/comment/v2/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable("postId")long postId,
                                             @RequestHeader("userId") Long userId){
        Post post = findPostService.findPostById(postId,userId);

        return ResponseEntity.ok().body(post);
    }
    @GetMapping("/comment/v1/item/{pageNum}/{type}/{size}")
    public ResponseEntity<SearchPost> findPagingPostByItemType(@PathVariable("pageNum") int pageNum,
                                                               @PathVariable("type") int type,
                                                               @PathVariable("size") int size){

        FindPagingPostRequest request = new FindPagingPostRequest(pageNum,type,size);
        SearchPost searchPost = findPostService.findPagingPostByType(request);

        return ResponseEntity.ok().body(searchPost);
    }

    @GetMapping("/comment/v1/community/{pageNum}/{type}/{size}")
    public ResponseEntity<SearchPost> findPagingPostByCommunityType(@PathVariable("pageNum") int pageNum,
                                                                    @PathVariable("type") int type,
                                                                    @PathVariable("size") int size){
        FindPagingPostRequest request = new FindPagingPostRequest(pageNum,type,size);
        SearchPost searchPost = findPostService.findPagingPostByCommunityType(request);

        return ResponseEntity.ok().body(searchPost);
    }

}
