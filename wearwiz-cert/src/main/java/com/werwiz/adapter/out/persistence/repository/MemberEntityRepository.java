package com.werwiz.adapter.out.persistence.repository;

import com.werwiz.adapter.out.persistence.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberEntityRepository extends JpaRepository<MemberEntity,Long> {
    boolean existsByEmail(String email);

    MemberEntity findByEmailAndPassword(String email,String password);


}
