package com.wearwiz.domain.frommember;


import com.wearwiz.adapter.entity.FromMemberCommunityEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_from_member")
@Builder
public class FromMemberEntity {

    @Id
    @Column(name = "from_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "viewFromMember")
    private List<FromMemberCommunityEntity> views;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "likeFromMember")
    private List<FromMemberCommunityEntity> likes;

    @Transient
    private FromMemberCommunityEntity detchView;

    @Transient
    private FromMemberCommunityEntity detchLike;

    public void addFromMemberView(FromMemberCommunityEntity fromMemberEntity) {
        fromMemberEntity.setViewFromMember(this);

        if (views == null) {
            views = new ArrayList<>();
        }

        views.add(fromMemberEntity);
    }

    public void addFromMemberLike(FromMemberCommunityEntity fromMemberCommunityEntity) {
        fromMemberCommunityEntity.setLikeFromMember(this);

        if (likes == null) {
            likes = new ArrayList<>();
        }

        likes.add(fromMemberCommunityEntity);
    }

    public boolean isView() {
        if (views == null) {
            return false;
        }

        for(FromMemberCommunityEntity view:views){
            if(view.getViewFromMember() == this){
                detchView = view;
                return true;
            }
        }

        return false;

    }

    public boolean isLike(){
        if(likes == null){
            return false;
        }

        for(FromMemberCommunityEntity like:likes){
            if(like.getLikeFromMember() == this){
                detchLike = like;
                return true;
            }
        }

        return false;
    }

    public boolean ifPresentByFromMemberCommunity(){
        boolean existFromMemberCommunity = false;
        if(isView()){
            existFromMemberCommunity = true;
        }

        if(isLike()){
            existFromMemberCommunity = true;
        }
        return existFromMemberCommunity;
    }


}
