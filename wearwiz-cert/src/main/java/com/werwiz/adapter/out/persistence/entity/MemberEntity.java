package com.werwiz.adapter.out.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="memberId")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_member") @Builder
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    private String name;

    private String email;

    @JsonIgnore
    private String password;

    private String area;

    private Integer counsel;

    private Double rate;

    @Column(name = "nickName", nullable = false)
    private String nickName;

    @Lob
    private String introduce;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private Set<MemberCategoryEntity> category;

    @ManyToOne(fetch = FetchType.LAZY)
    private PortfolioEntity portfolio;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    public Set<MemberRoleEntity> roleList;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public void addCategory(Set<MemberCategoryEntity> categoryEntities){
        for(MemberCategoryEntity categoryEntity : categoryEntities){
            this.addCategory(categoryEntity);
        }
    }
    public void addCategory(MemberCategoryEntity memberCategoryEntity){
        memberCategoryEntity.setMember(this);

        if(category == null){
            category = new HashSet<>();
        }

        category.add(memberCategoryEntity);
    }

    public void subtractCategory(MemberCategoryEntity memberCategoryEntity){
        this.category.remove(memberCategoryEntity);
    }

}
