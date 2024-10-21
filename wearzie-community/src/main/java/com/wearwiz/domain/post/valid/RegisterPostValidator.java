package com.wearwiz.domain.post.valid;

import com.wearwiz.domain.post.request.RegisterPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class RegisterPostValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RegisterPostValidator.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterPostRequest request = (RegisterPostRequest) target;

        if(request.getMemberId() == null){
            errors.rejectValue("memberId","invalid.memberId");
            return;
        }
    }
}
