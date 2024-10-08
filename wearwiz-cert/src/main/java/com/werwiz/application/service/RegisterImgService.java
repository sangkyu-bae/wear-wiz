package com.werwiz.application.service;

import com.wearwiz.common.UseCase;
import com.werwiz.adapter.out.persistence.entity.ImageEntity;
import com.werwiz.adapter.out.persistence.entity.MemberEntity;
import com.werwiz.adapter.out.persistence.mapper.ImageMapper;
import com.werwiz.application.port.in.JoinMemberUseCase;
import com.werwiz.application.port.in.RegisterImgUseCase;
import com.werwiz.application.port.out.FindMemberPort;
import com.werwiz.application.port.out.UploadImagePort;
import com.werwiz.domain.Image;
import com.werwiz.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class RegisterImgService implements RegisterImgUseCase {
    @Value("${upload.path}")
    private String uploadPath;

    private final FindMemberPort findMemberPort;

    private final ImageMapper imageMapper;
    private final UploadImagePort uploadImagePort;

    @Override
    @Transactional
    public List<Image> registerImgByMember(List<MultipartFile> registerImg,long memberId) {
        List<Image> images = new ArrayList<>();
        MemberEntity member = findMemberPort.findById(memberId);

        for(MultipartFile multipartFile : registerImg){
            boolean hasUploadError = !uploadFile(multipartFile);
            /**
             * todo:
             * eroor 처리 필요
             * */
            if(hasUploadError){
               continue;
            }

            images.add(Image.createImage(null, multipartFile.getName()));
        }

        List<Image> registerImage = new ArrayList<>();
        for(Image image : images){
            ImageEntity registerImgEntity = uploadImagePort.uploadImage(image,member.getPortfolio());
            registerImage.add(imageMapper.mapToDomain(registerImgEntity));
        }

        return registerImage;
    }

    private boolean uploadFile(MultipartFile multipartFile) {

        try{
            String fileName = multipartFile.getOriginalFilename();
            Path path = Paths.get(uploadPath + "/" + fileName);
            Files.write(path, multipartFile.getBytes());
        }catch (Exception e){
            return false;
        }

        return true;
    }
}
