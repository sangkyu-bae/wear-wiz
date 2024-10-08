package com.werwiz.application.port.in;

import com.werwiz.domain.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RegisterImgUseCase {

    List<Image> registerImgByMember(List<MultipartFile> registerImg,long memberId);
}
