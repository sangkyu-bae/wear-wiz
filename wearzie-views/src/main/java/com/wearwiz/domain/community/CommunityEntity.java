package com.wearwiz.domain.community;


import com.wearwiz.adapter.entity.FromMemberCommunityEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_community") @Builder
public class CommunityEntity {
    @Id
    @Column(name ="community_view_id")
    private Long id;

    private int viewCount;

    private int likeCount;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "community")
    private Set<FromMemberCommunityEntity> fromMembers;



    public void addFromMemberView(FromMemberCommunityEntity fromMemberEntity){
        fromMemberEntity.setCommunity(this);

        if(fromMembers == null){
            fromMembers = new HashSet<>();
        }

        this.fromMembers.add(fromMemberEntity);
    }

    public void increaseViewCount(){
        this.viewCount++;
    }

    public void increaseLikeCount(){
        this.likeCount++;
    }

}
