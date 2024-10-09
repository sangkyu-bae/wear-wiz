package com.werwiz.application.port.in;

import com.werwiz.adapter.in.request.LoginMemberRequest;
import com.werwiz.domain.Category;
import com.werwiz.domain.LoginMember;

import java.util.List;

public interface FindCategoryUseCase {
    List<Category> findAll();


}
