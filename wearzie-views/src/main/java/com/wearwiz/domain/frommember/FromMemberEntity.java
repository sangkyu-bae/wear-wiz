package com.wearwiz.domain.frommember;


import com.wearwiz.adapter.entity.FromMemberViewEntity;
import com.wearwiz.domain.community.like.CommunityLikeEntity;
import com.wearwiz.domain.community.view.CommunityViewEntity;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fromId;

    @ManyToOne(fetch = FetchType.LAZY)
    private CommunityLikeEntity communityLike;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "communityView")
    private List<FromMemberViewEntity> views;

    public void addFromMemberView(FromMemberViewEntity fromMemberViewEntity){
        fromMemberViewEntity.setFromMember(this);
        if(views == null){
            views = new ArrayList<>();
        }

        views.add(fromMemberViewEntity);
    }
}
