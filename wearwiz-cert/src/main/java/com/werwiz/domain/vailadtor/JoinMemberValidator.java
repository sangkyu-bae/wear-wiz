package com.werwiz.domain.vailadtor;

import com.werwiz.adapter.in.request.JoinMemberRequest;
import com.werwiz.adapter.out.persistence.repository.MemberEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinMemberValidator implements Validator {

    private final MemberEntityRepository memberEntityRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(JoinMemberRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        JoinMemberRequest request = (JoinMemberRequest) target;

        boolean hasError = request.validateSelfCheck();

        if(hasError){
            errors.rejectValue("vailid","invalid.joinform","valid 조건을 충족하지 않습니다.");
            return;
        }

        if(memberEntityRepository.existsByEmail(request.getEmail())){
            errors.rejectValue("email","invalid.email","이미 존재하는 이메일 입니다");
            return;
        }
        String pw = request.getPassword();
        String confirmPw = request.getConfirmPassword();

        if(!pw.equals(confirmPw)){
            errors.rejectValue("password","invalid.passwrod","password가 일치하지 않습니다");
            return;
        }

    }
}
