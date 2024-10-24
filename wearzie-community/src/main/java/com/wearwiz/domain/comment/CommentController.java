package com.wearwiz.domain.comment;


import com.wearwiz.domain.comment.request.RegisterCommentRequest;
import com.wearwiz.domain.comment.service.RegisterCommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
public class CommentController {

    private final RegisterCommentService registerCommentService;
    @PostMapping("/comment/v2/{postId}")
    public ResponseEntity<Comment> registerComment(
            @RequestBody RegisterCommentRequest request,
            @PathVariable("postId") long postId,
            @RequestHeader("userId") Long userId

//            Errors errors, Validator validator
    ){

        request.setMemberId(userId);
        request.setPostId(postId);
//        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(request, "registerPostRequest");
//        validator.validate(request, bindingResult);

//        if(errors.hasErrors()){
//            //에러 처리
//        }

        Comment createComment = registerCommentService.registerComment(request,postId);

        return ResponseEntity.ok().body(createComment);
    }
}
