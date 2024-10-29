package com.wearwiz.domain.frommember;


import com.wearwiz.adapter.entity.FromMemberCommunityEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_from_member") @Builder
public class FromMemberEntity {

    @Id
    @Column( name ="from_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "viewFromMember")
    private List<FromMemberCommunityEntity> views;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "likeFromMember")
    private List<FromMemberCommunityEntity> likes;

    public void addFromMemberView(FromMemberCommunityEntity fromMemberEntity){
        fromMemberEntity.setViewFromMember(this);

        if(views == null){
            views = new ArrayList<>();
        }

        views.add(fromMemberEntity);
    }

    public void addFromMemberLike(FromMemberCommunityEntity fromMemberCommunityEntity){
        fromMemberCommunityEntity.setLikeFromMember(this);

        if(likes == null){
            likes = new ArrayList<>();
        }

        likes.add(fromMemberCommunityEntity);
    }

}
